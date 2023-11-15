package com.mjl.blog.controller.admin.media;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.convert.admin.MediaAdminConvert;
import com.mjl.blog.service.admin.media.MediaAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin/media")
@RestController
@Validated
public class MediaAdminController {

    @Resource
    private MediaAdminService mediaAdminService;

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "发布自媒体文章")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(mediaAdminService.create(createReqVO) > 0 ? true : false);
    }

    /*
    *获取前preNum天的文章
     */
    @GetMapping("/date-counts")
    @Operation(summary = "获取前preNum天的文章")
    public CommonResult<DateCountsRespVO> getDateCounts(int preNum){
        DateCountsRespVO dateCountsRespVO = mediaAdminService.getDateCounts(preNum);
        return success(dateCountsRespVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取自媒体文章通过ID")
    public CommonResult<MediaRespVO> get(Long id){
        return success(MediaAdminConvert.INSTANCE.convert(mediaAdminService.getById(id)));
    }

    @GetMapping("/table")
    @Operation(summary = "获取表单信息")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(MediaAdminConvert.INSTANCE.convert(mediaAdminService.getList(tableReqVO)));
    }

    @PutMapping ("/update-status")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新文章状态")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        mediaAdminService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新文章")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        mediaAdminService.update(updateReqVO);
        return success(true);
    }

    @PutMapping ("/update-tops")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新置顶文章")
    public CommonResult<Boolean> updateTops(@RequestBody UpdateTopsReqVO updateTopsReqVO){
        mediaAdminService.updateTops(updateTopsReqVO);
        return success(true);
    }

}
