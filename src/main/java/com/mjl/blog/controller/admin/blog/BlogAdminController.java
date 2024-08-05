package com.mjl.blog.controller.admin.blog;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.convert.admin.BlogAdminConvert;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/type-counts")
    @Operation(summary = "获取各个类型文章的数量")
    public CommonResult<BlogTypeCountsRespVO> getBlogTypeCounts(){
        BlogTypeCountsRespVO blogTypeCountsRespVO = blogAdminService.getArticleTypeCounts();
        return success(blogTypeCountsRespVO);
    }

    /*
     * 获取各个类型文章的数量
     */
    @GetMapping("/test")
    @Operation(summary = "测试")
    public CommonResult<BlogTypeCountsRespVO> test(){
        BlogTypeCountsRespVO blogTypeCountsRespVO = blogAdminService.getArticleTypeCounts();
        return success(blogTypeCountsRespVO);
    }

    /*
    *获取前preNum天的文章
     */
    @GetMapping("/date-counts")
    @Operation(summary = "获取前preNum天的文章")
    public CommonResult<BlogDateCountsRespVO> getBlogDateCounts(int preNum){
        BlogDateCountsRespVO blogDateCountsRespVO = blogAdminService.getBlogDateCounts(preNum);
        return success(blogDateCountsRespVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取文章信息根据ID")
    public CommonResult<BlogRespVO> get(Long id){
        return success(BlogAdminConvert.INSTANCE.convert(blogAdminService.getBlogById(id)));
    }

    @GetMapping("/table")
    @Operation(summary = "获取文章表单")
    public CommonResult<PageResult<BlogTableRespVO>> getBlogTable(BlogTableReqVO blogTableReqVO){
        return success(BlogAdminConvert.INSTANCE.convert(blogAdminService.getBlogList(blogTableReqVO)));
    }

    @PutMapping ("/update-status")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新文章状态")
    public CommonResult<Boolean> updateBlogsStatus(@RequestBody UpdateBlogsStatusReqVO updateBlogsStatusReqVO){
        blogAdminService.updateBlogsStatus(updateBlogsStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update-tops")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新置顶")
    public CommonResult<Boolean> updateBlogsTop(@RequestBody UpdateBlogsTopReqVO updateBlogsTopReqVO){
        blogAdminService.updateBlogsTop(updateBlogsTopReqVO);
        return success(true);
    }

    @PutMapping ("/update-recommends")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新推荐")
    public CommonResult<Boolean> updateBlogsRecommend(@RequestBody UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO){
        blogAdminService.updateBlogsRecommend(updateBlogsRecommendReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新文章信息")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        blogAdminService.update(updateReqVO);
        return success(true);
    }

    @PostMapping ("/create-auto")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "自动创建文章")
    public CommonResult<Boolean> autoCreate(@RequestBody @Valid UpdateReqVO updateReqVO){
        blogAdminService.update(updateReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "发布文章")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(blogAdminService.create(createReqVO) > 0 ? true : false);
    }

}
