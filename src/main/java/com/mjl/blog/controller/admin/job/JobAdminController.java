package com.mjl.blog.controller.admin.job;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.convert.admin.JobAdminConvert;
import com.mjl.blog.dal.dataobject.JobDO;
import com.mjl.blog.service.admin.job.JobAdminService;
import com.mjl.blog.service.admin.type.TypeAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/job")
public class JobAdminController {

    @Resource
    private JobAdminService jobService;
    @Resource
    private TypeAdminService typeService;

    @GetMapping("/list")
    @Operation(summary = "获取任务列表")
    public CommonResult<List<JobListVO>> getJobList(){
        return CommonResult.success(JobAdminConvert.INSTANCE.convert(jobService.getJobList()));
    }

    @GetMapping("/get")
    @Operation(summary = "获取任务内容")
    public CommonResult<GetRespVO> get(Long id){
        return success(JobAdminConvert.INSTANCE.convert2(jobService.getById(id)));
    }

    @GetMapping("/table")
    @Operation(summary = "获取任务表单")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        PageResult<JobDO> jobDOPageResult = jobService.getList(tableReqVO);
        return success(JobAdminConvert.INSTANCE.convert(jobDOPageResult));
    }


    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新任务状态")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        jobService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新任务信息")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        jobService.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务")
    public CommonResult<Boolean> delete(@RequestBody DeleteReqVO deleteReqVO){
        jobService.delete(deleteReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "创建任务")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(jobService.create(createReqVO) > 0 ? true : false);
    }

}
