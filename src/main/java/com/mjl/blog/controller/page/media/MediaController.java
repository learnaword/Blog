package com.mjl.blog.controller.page.blog;

import com.mjl.blog.annotation.ServiceLimit;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.common.utils.PageInfo;
import com.mjl.blog.controller.page.blog.vo.*;
import com.mjl.blog.controller.page.media.vo.*;
import com.mjl.blog.convert.page.MediaConvert;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.service.page.media.MediaService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/media")
public class MediaController {

    @Resource
    private MediaService mediaService;

    @Value("${app.base-url}")
    private String baseUrl;

    @RequestMapping("/media.html")
    public String media(Model model, MediaReqVO mediaReqVO){

        //获取软件全部软件
        PageResult<NewListRespVO> mediaNewList = MediaConvert.INSTANCE.covertNew(mediaService.getNewList(mediaReqVO));
        PageInfo<NewListRespVO> pageInfo = new PageInfo<>(mediaNewList,mediaReqVO,3);

        List<TopMediasRespVO> topBlogs = mediaService.getTopMedias();
        List<NewMediasRespVO> newBlogs = MediaConvert.INSTANCE.convertNew(mediaService.getNewMedias());


        model.addAttribute("mediaNewList", mediaNewList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("newBlogs", newBlogs);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/media/media";
    }

    @RequestMapping({"/find/{id}.html"})
    @ServiceLimit
    public String selectMediaById(@PathVariable Long id, Model model){
        if (id != null && id > 0) {
            MediaDO media = mediaService.selectMediaById(id);
            if (media == null) {
                model.addAttribute("status", 404);
                return "/page/majunliangdas";
            }

            MediaInfoRespVO mediaInfoRespVO = MediaConvert.INSTANCE.convert(media);
            //随机获取4篇文章对应的专区文章
            List<RelMediasRespVO> relMedias = mediaService.getRelMedias(media);
            //随机获取6篇文章，放在热门
            List<HotMediasRespVO> hotMedias = mediaService.getHotMedias(media);
            //获取4篇文章对应的专区置顶文章。
            List<TopMediasRespVO> topMedias = mediaService.getTopMedias();
            //获取10篇最新的文章
            List<NewMediasRespVO> newMedias = MediaConvert.INSTANCE.convertNew(mediaService.getNewMedias());

            //获取上一篇和下一篇
            NextAndPreBlogRespVO nextBlog = MediaConvert.INSTANCE.convertNextAndPre(mediaService.selectNextMedia(id));
            NextAndPreBlogRespVO prevBlog =  MediaConvert.INSTANCE.convertNextAndPre(mediaService.selectPrevMedia(id));

            model.addAttribute("relMedias", relMedias);
            model.addAttribute("hotMedias", hotMedias);
            model.addAttribute("topMedias", topMedias);
            model.addAttribute("newMedias", newMedias);
            model.addAttribute("next", nextBlog);
            model.addAttribute("prev", prevBlog);
            model.addAttribute("media", mediaInfoRespVO);
            model.addAttribute("baseUrl",baseUrl);
        } else {
            model.addAttribute("status", 0);
        }
        return "/page/media/info";
    }
}
