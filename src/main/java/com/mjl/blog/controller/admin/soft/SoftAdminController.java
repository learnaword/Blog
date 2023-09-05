package com.mjl.blog.controller.admin.soft;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.controller.admin.soft.vo.SoftListVO;
import com.mjl.blog.convert.SoftConvert;
import com.mjl.blog.service.admin.soft.SoftService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/soft")
public class SoftAdminController {

    @Resource
    private SoftService softService;

    @GetMapping("/softList")
    public CommonResult<List<SoftListVO>> getSoftList(){
        return CommonResult.success(SoftConvert.INSTANCE.convert(softService.getSoftList()));
    }
}
