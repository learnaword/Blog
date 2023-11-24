package com.mjl.blog.controller.admin.log;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.blog.vo.BlogTypeCountsRespVO;
import com.mjl.blog.service.admin.log.LogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    LogService logService;

    /*
     * 获取日志总数
     */
    @GetMapping("/log-counts")
    @Operation(summary = "获取日志总数")
    public CommonResult<Long> getLogCounts(){
        Long counts = logService.getLogCounts();
        return success(counts);
    }

    /*
     * 获取日志总数
     */
    @GetMapping("/log-now")
    @Operation(summary = "获取当天总数")
    public CommonResult<Long> getLogNowCounts(){
        Long counts = logService.getLogNowCounts();
        return success(counts);
    }

}
