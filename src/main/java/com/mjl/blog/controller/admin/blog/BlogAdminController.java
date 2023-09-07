package com.mjl.blog.controller.admin.blog;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.convert.admin.BlogAdminConvert;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin/blog")
@RestController
@Validated
public class BlogAdminController {

    @Resource
    private BlogAdminService blogAdminService;

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

    @GetMapping("/blogTable")
    public CommonResult<PageResult<BlogTableRespVO>> getBlogTable(BlogTableReqVO blogTableReqVO){
        return success(BlogAdminConvert.INSTANCE.convert(blogAdminService.getBlogList(blogTableReqVO)));
    }

    @PostMapping ("/updateBlogsStatus")
    public CommonResult<Boolean> updateBlogsStatus(@RequestBody UpdateBlogsStatusReqVO updateBlogsStatusReqVO){
        blogAdminService.updateBlogsStatus(updateBlogsStatusReqVO);
        return success(true);
    }

    @PostMapping ("/updateBlogsTop")
    public CommonResult<Boolean> updateBlogsTop(@RequestBody UpdateBlogsTopReqVO updateBlogsTopReqVO){
        blogAdminService.updateBlogsTop(updateBlogsTopReqVO);
        return success(true);
    }

    @PostMapping ("/updateBlogsRecommend")
    public CommonResult<Boolean> updateBlogsRecommend(@RequestBody UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO){
        blogAdminService.updateBlogsRecommend(updateBlogsRecommendReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(blogAdminService.create(createReqVO) > 0 ? true : false);
    }

    @PostMapping ("/update")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        blogAdminService.update(updateReqVO);
        return success(true);
    }

    @PostMapping ("/autoCreate")
    public CommonResult<Boolean> autoCreate(@RequestBody @Valid UpdateReqVO updateReqVO){
        blogAdminService.update(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<BlogRespVO> get(Long id){
        return success(BlogAdminConvert.INSTANCE.convert(blogAdminService.getBlogById(id)));
    }

}
