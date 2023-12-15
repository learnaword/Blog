package com.mjl.blog.service.admin.auto.blog;

import com.mjl.blog.controller.admin.auto.blog.vo.CreateReqVO;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.SentenceDO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.mysql.BlogMapper;
import com.mjl.blog.service.admin.auto.config.AutoConfigAdminService;
import com.mjl.blog.service.admin.auto.sentence.SentenceAdminService;
import com.mjl.blog.service.admin.soft.SoftAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;
import static com.mjl.blog.enums.ErrorCodeConstants.INVALID_TEMPLATE;

@Service
public class AutoBlogServiceAdminImpl implements AutoBlogAdminService {

    @Resource
    private SentenceAdminService sentenceService;
    @Resource
    private AutoConfigAdminService autoConfigService;
    @Resource
    private SoftAdminService softService;
    @Resource
    private BlogMapper blogMapper;
    @Override
    public void create(CreateReqVO createReqVO) {
        //从数据库获取两个随机句子。
        List<SentenceDO> sentenceDOList =  sentenceService.getListLimit(1);
        //根据文章标题autoConfig获取到配置信息。
        AutoConfigDO autoConfigDO = autoConfigService.getByBlogTitle(createReqVO.getTitle());

        if(createReqVO.getAutoConfig() !=0){
            autoConfigDO = autoConfigService.getById(createReqVO.getAutoConfig());
        }

        if(autoConfigDO==null){
            //异常，没有可用模版
            throw exception(INVALID_TEMPLATE);
        }

        SoftDO softDO = softService.getSoftById(autoConfigDO.getSoftId());
        String contentImges = "";

        if(!createReqVO.getContent().contains("img")){
            contentImges ="<img src=\"" +autoConfigDO.getContentImages()+ "\" style=\"width: 339.486px; float: none;\" class=\"imageCenter\"><br>";
        }

        String[] sentenceHead =sentenceDOList.get(0).getContent().split("，");
        String introduction =autoConfigDO.getTitle() + sentenceHead[0] + "，" + sentenceHead[1] + "，下面回答的问题是“" + createReqVO.getTitle() + "”，希望可以帮到你。";
        String contentHtml = getContentHtml(softDO,sentenceHead,createReqVO,autoConfigDO,contentImges,false);

        BlogDO blogDO = new BlogDO();
        blogDO.setImages( autoConfigDO.getImages() );
        blogDO.setRankScore( autoConfigDO.getRankScore() );
//        List<Integer> adTypes = Arrays.asList(127, 128, 126, 129);;
        blogDO.setAdTypes(autoConfigDO.getAdTypes());
        blogDO.setIsTop( autoConfigDO.getIsTop() );
        blogDO.setIsRecommend( autoConfigDO.getIsRecommend() );
        blogDO.setSoftId( autoConfigDO.getSoftId() );
        blogDO.setSoftSection( autoConfigDO.getSoftSection() );
        blogDO.setTitle(createReqVO.getTitle()+"？");
        blogDO.setContent(contentHtml);
        blogDO.setCreateTime(System.currentTimeMillis());
        blogDO.setUpdateTime(System.currentTimeMillis());
        blogDO.setKeyword(createReqVO.getTitle());
        blogDO.setIntroduction(introduction);
        blogDO.setStatus(createReqVO.getStatus());
        blogMapper.insert(blogDO);

        //更新句子使用次数
        sentenceService.updateList(sentenceDOList);
    }

    public String getContentHtml(SoftDO softDO,String[] sentenceHead,CreateReqVO createReqVO,AutoConfigDO autoConfigDO,String contentImges,boolean hasImage){
        String contentHtml = "<p>" + softDO.getTitle() + sentenceHead[0] + "，" + sentenceHead[1] + "，下面回答的问题是“" + createReqVO.getTitle() + "”，希望可以帮到你。" +
                "<h2 id=\"nav1_1\">" + createReqVO.getTitle() +
                "</h2><div>" +
                autoConfigDO.getRecommendHtml()+"<br>" +
                "<span style=\"font-size:17px;font-weight: bold;color: #49a380\">问题答案</span><br><br>" +
                createReqVO.getContent();

        if(hasImage){
            List<SentenceDO> sentenceDOList =  sentenceService.getListLimit(1);
            contentHtml += "<br>"+
                    contentImges +
                    "以上就是" +
                    createReqVO.getTitle() +
                    "的全部内容了，" +
                    softDO.getTitle()  +
                    sentenceDOList.get(0).getContent() +
                    "。</div>";
        }else{
            contentHtml += "</div>";
        }

        return contentHtml;
    }

    public void test(){

    }
}
