package com.mjl.blog.controller.admin.soft;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.*;
import com.mjl.blog.convert.SoftConvert;
import com.mjl.blog.service.admin.soft.SoftService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/soft")
public class SoftAdminController {

    @Resource
    private SoftService softService;

    @GetMapping("/softList")
    public CommonResult<List<SoftListVO>> getSoftList(){
        return CommonResult.success(SoftConvert.INSTANCE.convert(softService.getSoftList()));
    }

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(SoftConvert.INSTANCE.convert(softService.getList(tableReqVO)));
    }


    @PostMapping("/updateStatus")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        softService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    public CommonResult<Boolean> delete(@RequestBody DeleteReqVO deleteReqVO){
        softService.delete(deleteReqVO);
        return success(true);
    }


    @PostMapping ("/create")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(softService.create(createReqVO) > 0 ? true : false);
    }

    @PostMapping ("/update")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        softService.update(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<GetRespVO> get(Long id){
        return success(SoftConvert.INSTANCE.convert2(softService.getById(id)));
    }
}
