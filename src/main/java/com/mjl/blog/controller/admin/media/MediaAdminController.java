package com.mjl.blog.controller.admin.media;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.convert.admin.MediaAdminConvert;
import com.mjl.blog.service.admin.media.MediaAdminService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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

    /*
    *获取前preNum天的文章
     */
    @GetMapping("/dateCounts")
    public CommonResult<DateCountsRespVO> getDateCounts(int preNum){
        DateCountsRespVO dateCountsRespVO = mediaAdminService.getDateCounts(preNum);
        return success(dateCountsRespVO);
    }

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(MediaAdminConvert.INSTANCE.convert(mediaAdminService.getList(tableReqVO)));
    }

    @PostMapping ("/updateStatus")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        mediaAdminService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PostMapping ("/updateTops")
    public CommonResult<Boolean> updateTops(@RequestBody UpdateTopsReqVO updateTopsReqVO){
        mediaAdminService.updateTops(updateTopsReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(mediaAdminService.create(createReqVO) > 0 ? true : false);
    }

    @PostMapping ("/update")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        mediaAdminService.update(updateReqVO);
        return success(true);
    }

    @PostMapping ("/autoCreate")
    public CommonResult<Boolean> autoCreate(@RequestBody @Valid UpdateReqVO updateReqVO){
        mediaAdminService.update(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<MediaRespVO> get(Long id){
        return success(MediaAdminConvert.INSTANCE.convert(mediaAdminService.getById(id)));
    }

}
