package com.mjl.blog.controller.admin.echars;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import com.mjl.blog.service.admin.log.LogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin/echars")
@RestController
public class EcharsController {

    @Resource
    private BlogAdminService blogAdminService;

    @Resource
    private LogService logService;
    @GetMapping("/log-data")
    @Operation(summary = "获取操作记录")
    public CommonResult<DataRespVO> echarLogData(@RequestParam("start") Long start,
                                                 @RequestParam("end") Long end){
        return success(logService.getEcharsLogData(start, end));
    }

    @GetMapping("/blog-data")
    @Operation(summary = "获取文章的发表数量")
    public CommonResult<DataRespVO> echarBlogData(@RequestParam("start") Long start,
                                                  @RequestParam("end") Long end){
        return success(blogAdminService.getEcharsBlogData(start, end));
    }
}
