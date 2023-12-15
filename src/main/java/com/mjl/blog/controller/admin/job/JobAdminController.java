package com.mjl.blog.controller.admin.job;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.convert.admin.JobAdminConvert;
import com.mjl.blog.dal.dataobject.JobDO;
import com.mjl.blog.service.admin.job.JobAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;
@RestController
@RequestMapping("/admin/job")
public class JobAdminController {
    @Resource
    private JobAdminService jobService;

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
    @Operation(summary = "更新定时任务的状态")
    public CommonResult<Boolean> updateJobStatus(@RequestParam(value = "id") Long id, @RequestParam("status") Integer status)
            throws SchedulerException {
        jobService.updateJobStatus(id, status);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除定时任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<Boolean> deleteJob(@RequestParam("id") Long id)
            throws SchedulerException {
        jobService.deleteJob(id);
        return success(true);
    }

    @PutMapping("/trigger")
    @Operation(summary = "触发定时任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<Boolean> triggerJob(@RequestParam("id") Long id) throws SchedulerException {
        jobService.triggerJob(id);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新定时任务")
    public CommonResult<Boolean> updateJob(@Valid @RequestBody UpdateReqVO updateReqVO)
            throws SchedulerException {
        jobService.updateJob(updateReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "创建任务")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO) throws SchedulerException {
        return success(jobService.createJob(createReqVO) > 0 ? true : false);
    }

}
