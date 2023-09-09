package com.mjl.blog.controller.admin.auto.config;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.config.vo.*;
import com.mjl.blog.convert.admin.AutoConfigAdminConvert;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.service.admin.auto.config.AutoConfigAdminService;
import com.mjl.blog.service.admin.soft.SoftAdminService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RequestMapping("/admin/auto-config")
@RestController
@Validated
public class AutoConfigAdminController {

    @Resource
    private AutoConfigAdminService autoConfigService;
    @Resource
    private SoftAdminService softService;

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getBlogTable(TableReqVO tableReqVO){

        PageResult<AutoConfigDO> configDOPageResult = autoConfigService.getList(tableReqVO);

        List<TableRespVO> autoConfigList = new ArrayList<>( configDOPageResult.getList().size() );
        configDOPageResult.getList().forEach(item ->{
            TableRespVO autoConfigTableRespVO = AutoConfigAdminConvert.INSTANCE.convert(item);
            autoConfigTableRespVO.setSoftTitle(softService.getSoftById(item.getSoftId()).getTitle());
            autoConfigList.add( autoConfigTableRespVO );
        });

        return success(new PageResult<>(autoConfigList,configDOPageResult.getTotal()));
    }

    @GetMapping("/list")
    public CommonResult<List<AutoConfigDO>> getList(){
        return CommonResult.success(autoConfigService.getList());
    }

    @PostMapping ("/updateConfigStatus")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> updateConfigStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        autoConfigService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(autoConfigService.create(createReqVO) > 0 ? true : false);
    }

    @PostMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        autoConfigService.update(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<GetRespVO> get(Long id){
        return success(AutoConfigAdminConvert.INSTANCE.convert2(autoConfigService.getById(id)));
    }

}