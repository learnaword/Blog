package com.mjl.blog.controller.page.technology.blog;

import com.mjl.blog.annotation.SystemLog;
import com.mjl.blog.common.exception.ServerException;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.PageInfo;
import com.mjl.blog.controller.page.technology.blog.vo.OrderListRespVO;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.controller.page.technology.blog.vo.*;
import com.mjl.blog.convert.page.SoftConvert;
import com.mjl.blog.convert.page.TechnologyBlogConvert;
import com.mjl.blog.convert.page.TechnologySoftConvert;
import com.mjl.blog.dal.dataobject.*;
import com.mjl.blog.service.page.technology.blog.TechnologyBlogService;
import com.mjl.blog.service.page.technology.soft.TechnologySoftService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.mjl.blog.common.enums.GlobalErrorCodeConstants.TOO_MANY_REQUESTS;

@Controller
@RequestMapping("/technology")
public class TechnologyBlogController {

    @Resource
    private TechnologyBlogService technologyblogService;

    @Resource
    private TechnologySoftService technologysoftService;

    @Value("${app.base-url}")
    private String baseUrl;

    @RequestMapping("/technology.html")
    public String index(Model model, SoftReqVO softReqVO) {

        List<OrderListRespVO> blogOrderList = TechnologyBlogConvert.INSTANCE.covertOrder(technologyblogService.getOrderList());
        List<NewBlogsTechRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyblogService.getNewBlogs());

        PageResult<SoftListRespVO> softList = TechnologySoftConvert.INSTANCE.covert(technologysoftService.getList(softReqVO));

        PageInfo<SoftListRespVO> pageInfo = new PageInfo<>(softList, softReqVO, 3);
        model.addAttribute("topBlogs", blogOrderList);
        model.addAttribute("newBlogs", newBlogs);
        model.addAttribute("softList", softList.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("baseUrl", baseUrl);

        return "/page/technology/technology";
    }

    @RequestMapping({"/find/{id}.html"})
    @SystemLog(description = "访问了文章", userType = "游客")
    public ModelAndView selectBlogById(@PathVariable @NotNull(message = "id不能为空") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        TechnologyBlogDO blog = technologyblogService.selectBlogById(id);
        if (blog == null) {
            modelAndView.setViewName("/page/error/404");
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            // 指定HTTP状态码为404
            return modelAndView;
        }
        BlogInfoRespVO blogInfoRespVO = TechnologyBlogConvert.INSTANCE.convert(blog);
        TechnologySoftDO softDO = technologysoftService.selectById(blog.getSoftId());
        blogInfoRespVO.setSoft(softDO);

        //随机获取4篇文章对应的专区文章
        List<RelBlogsRespVO> relBlogs = technologyblogService.getRelBlogs(blog);
        //随机获取6篇文章，放在热门
        List<RelBlogsRespVO> hotBlogs = technologyblogService.getHotBlogs(blog);
        //获取4篇文章对应的专区置顶文章。
        List<TopBlogsRespVO> topBlogs = technologyblogService.getTopBlogs(blog);
        //获取10篇最新的文章
        List<NewBlogsTechRespVO> newBlogs = TechnologyBlogConvert.INSTANCE.convertNew(technologyblogService.getNewBlogs());

        //获取上一篇和下一篇
        NextAndPreBlogRespVO nextBlog = TechnologyBlogConvert.INSTANCE.convertNextAndPre(technologyblogService.selectNextBlog(id, blog.getSoftId()));
        NextAndPreBlogRespVO prevBlog = TechnologyBlogConvert.INSTANCE.convertNextAndPre(technologyblogService.selectPrevBlog(id, blog.getSoftId()));

        modelAndView.addObject("relBlogs", relBlogs);
        modelAndView.addObject("hotBlogs", hotBlogs);
        modelAndView.addObject("topBlogs", topBlogs);
        modelAndView.addObject("newBlogs", newBlogs);
        modelAndView.addObject("next", nextBlog);
        modelAndView.addObject("prev", prevBlog);
        modelAndView.addObject("blog", blogInfoRespVO);
        modelAndView.addObject("baseUrl", baseUrl);
        modelAndView.setViewName("/page/technology/info");
        return modelAndView;
    }

    @GetMapping({"/blog/result.html"})
    public String searchResult(@RequestParam("keyboard") String keyboard, Model model) {
        System.out.println(keyboard);
        System.out.println("---------------------");
        if (keyboard != null) {
            List<ResultRespVO> resultRespVO = TechnologyBlogConvert.INSTANCE.convertResult(technologyblogService.find(keyboard));
            model.addAttribute("result", resultRespVO);
            model.addAttribute("baseUrl", baseUrl);
        }
        return "/page/result";
    }

    private String indexFallback(Model model, IndexReqVO indexReqVO, Throwable throwable) {
        throw new ServerException(TOO_MANY_REQUESTS);
    }

    public ModelAndView selectBlogByIdFallback(Long id, Throwable throwable) {
        throw new ServerException(TOO_MANY_REQUESTS);
    }
}
