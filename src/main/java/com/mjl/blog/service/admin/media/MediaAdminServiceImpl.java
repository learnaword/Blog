package com.mjl.blog.service.admin.media;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.convert.admin.MediaAdminConvert;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.dal.mysql.MediaMapper;
import jakarta.annotation.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaAdminServiceImpl implements MediaAdminService {
    @Resource
    private MediaMapper mediaMapper;

    @Override
    public DateCountsRespVO getDateCounts(int preNum) {
        DateCountsRespVO dateCountsRespVO = new DateCountsRespVO();
        LocalDate today = LocalDate.now();
        ArrayList<Long> dateCounts = new ArrayList<Long>();
        for (int i = 0; i < preNum; i++) {
            Long preDate = today.minusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = today.minusDays(i).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = mediaMapper.selectCount(new LambdaQueryWrapper<MediaDO>()
                    .between(MediaDO::getCreateTime,preDate,nowDate)
                    .eq(MediaDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));
            dateCounts.add(sum);
        }
        dateCountsRespVO.setDateCounts(dateCounts);
        return dateCountsRespVO;
    }

    @Override
    public PageResult<MediaDO> getList(TableReqVO tableReqVO) {
        return mediaMapper.selectPage(tableReqVO);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<MediaDO> mediaDOList =  mediaMapper.selectList(new LambdaQueryWrapper<MediaDO>().in(MediaDO::getId,updateStatusReqVO.getIds()));
        mediaDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        mediaMapper.updateBatch(mediaDOList);
    }

    @Override
    public void updateTops(UpdateTopsReqVO updateTopsReqVO) {
        List<MediaDO> mediaDOList =  mediaMapper.selectList(new LambdaQueryWrapper<MediaDO>().in(MediaDO::getId,updateTopsReqVO.getIds()));
        mediaDOList.forEach(item -> item.setIsTop(updateTopsReqVO.getIsTop()).setUpdateTime(System.currentTimeMillis()));
        mediaMapper.updateBatch(mediaDOList);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        MediaDO mediaDO = MediaAdminConvert.INSTANCE.convert(createReqVO);
        setDefaultValue(mediaDO,createReqVO);
        mediaDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        mediaDO.setCreateTime(System.currentTimeMillis());
        mediaDO.setUpdateTime(System.currentTimeMillis());
        return mediaMapper.insert(mediaDO);
    }

    @Override
    public MediaDO getById(Long id) {
        return mediaMapper.selectById(id);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        MediaDO mediaDO = MediaAdminConvert.INSTANCE.convert(updateReqVO);
        mediaDO.setUpdateTime(System.currentTimeMillis());
        mediaMapper.updateById(mediaDO);
    }


    private  String parseIntroduction(String contextHtml,int limit){
        Document document = Jsoup.parse(contextHtml);

        String contexText = document.body().text();

        // 提取前50个字符的文本，包括HTML标签
        String firstChars = contexText.substring(0, Math.min(contexText.length(),limit)).replace("。","。<br>");

        return firstChars;
    }

    private void setDefaultValue(MediaDO mediaDO,CreateReqVO createReqVO) {
        if(createReqVO.getIntroduction().isEmpty()){
            mediaDO.setIntroduction(parseIntroduction(createReqVO.getContent(),70));
        }

        if(createReqVO.getKeyword().isEmpty()){
            mediaDO.setKeyword(createReqVO.getTitle());
        }
    }
}
