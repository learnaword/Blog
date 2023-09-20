package com.mjl.blog.controller.page.blog;

import com.mjl.blog.annotation.ServiceLimit;
import com.mjl.blog.annotation.SystemLog;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.common.utils.PageInfo;
import com.mjl.blog.controller.page.blog.vo.*;
import com.mjl.blog.convert.page.BlogConvert;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.service.page.blog.BlogService;
import com.mjl.blog.service.page.soft.SoftService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.charset.Charset;
import java.util.List;

@Controller
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private SoftService softService;
    @Value("${app.base-url}")
    private String baseUrl;

    @RequestMapping("/")
    @ServiceLimit
    public String index(Model model, IndexReqVO indexReqVO){

        List<OrderListRespVO> blogOrderList = BlogConvert.INSTANCE.covertOrder(blogService.getOrderList());
        PageResult<NewListRespVO> blogNewList = BlogConvert.INSTANCE.covertNew(blogService.getNewList(indexReqVO));

        PageInfo<NewListRespVO> pageInfo = new PageInfo<>(blogNewList,indexReqVO,3);
        model.addAttribute("blogOrderList", blogOrderList);
        model.addAttribute("blogNewList", blogNewList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("baseUrl",baseUrl);

        return "/page/index";
    }

    @RequestMapping({"/find/{id}.html"})
    @SystemLog(description = "访问了文章", userType = "游客")
    @ServiceLimit
    public String selectBlogById(@PathVariable Long id, Model model)  {
        if (id != null && id > 0) {
            BlogDO blog = blogService.selectBlogById(id);
            if (blog == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
            }

            BlogInfoRespVO blogInfoRespVO = BlogConvert.INSTANCE.convert(blog);
            SoftDO softDO = softService.selectById(blog.getSoftId());
            blogInfoRespVO.setSoft(softDO);

            //随机获取4篇文章对应的专区文章
            List<RelBlogsRespVO> relBlogs = blogService.getRelBlogs(blog);
            //随机获取6篇文章，放在热门
            List<RelBlogsRespVO> hotBlogs = blogService.getHotBlogs(blog);
            //获取4篇文章对应的专区置顶文章。
            List<TopBlogsRespVO> topBlogs = blogService.getTopBlogs(blog);
            //获取10篇最新的文章
            List<NewBlogsRespVO> newBlogs = BlogConvert.INSTANCE.convertNew(blogService.getNewBlogs(blog));

            //获取上一篇和下一篇
            NextAndPreBlogRespVO nextBlog = BlogConvert.INSTANCE.convertNextAndPre(blogService.selectNextBlog(id, blog.getSoftId()));
            NextAndPreBlogRespVO prevBlog =  BlogConvert.INSTANCE.convertNextAndPre(blogService.selectPrevBlog(id, blog.getSoftId()));

            model.addAttribute("relBlogs", relBlogs);
            model.addAttribute("hotBlogs", hotBlogs);
            model.addAttribute("topBlogs", topBlogs);
            model.addAttribute("newBlogs", newBlogs);
            model.addAttribute("next", nextBlog);
            model.addAttribute("prev", prevBlog);
            model.addAttribute("blog", blogInfoRespVO);
            model.addAttribute("baseUrl",baseUrl);
        } else {
            model.addAttribute("status", 0);
        }
        return "/page/info";
    }

    @GetMapping({"/blog/result.html"})
    public String searchResult(@RequestParam("keyboard") String keyboard, Model model) {
        System.out.println(keyboard);
        System.out.println("---------------------");
        if (keyboard != null) {
           List<ResultRespVO> resultRespVO = BlogConvert.INSTANCE.convertResult(blogService.find(keyboard));
            model.addAttribute("result",resultRespVO);
            model.addAttribute("baseUrl",baseUrl);
        }
        return "/page/result";
    }
}
