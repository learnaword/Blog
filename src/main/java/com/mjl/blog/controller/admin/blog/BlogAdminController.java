package com.mjl.blog.controller.admin.blog;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.blog.vo.BlogDateCountsRespVO;
import com.mjl.blog.controller.admin.blog.vo.BlogTypeCountsRespVO;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin")
@RestController
public class BlogAdminController {

    @Resource
    BlogAdminService blogAdminService;

    /*
    * 获取各个类型文章的数量
     */
    @GetMapping("/blogTypeCounts")
    public CommonResult<BlogTypeCountsRespVO> getBlogTypeCounts(){
        BlogTypeCountsRespVO blogTypeCountsRespVO = blogAdminService.getArticleTypeCounts();
        return success(blogTypeCountsRespVO);
    }

    /*
    *获取前preNum天的文章
     */
    @GetMapping("/blogDateCounts")
    public CommonResult<BlogDateCountsRespVO> getBlogDateCounts(int preNum){
        BlogDateCountsRespVO blogDateCountsRespVO = blogAdminService.getBlogDateCounts(preNum);
        return success(blogDateCountsRespVO);
    }
}
