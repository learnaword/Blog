package com.mjl.blog.controller.admin.technology.soft;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.soft.vo.*;
import com.mjl.blog.convert.admin.SoftAdminConvert;
import com.mjl.blog.convert.admin.TechnologySoftAdminConvert;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import com.mjl.blog.service.admin.soft.SoftAdminService;
import com.mjl.blog.service.admin.technology.soft.TechnologySoftAdminService;
import com.mjl.blog.service.admin.technology.type.TechnologyTypeAdminService;
import com.mjl.blog.service.admin.type.TypeAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/technology/soft")
public class TechnologySoftAdminController {

    @Resource
    private TechnologySoftAdminService softService;
    @Resource
    private TechnologyTypeAdminService typeService;

    @GetMapping("/list")
    @Operation(summary = "获取软件列表")
    public CommonResult<List<SoftListVO>> getSoftList(){
        return CommonResult.success(TechnologySoftAdminConvert.INSTANCE.convert(softService.getSoftList()));
    }

    @GetMapping("/get")
    @Operation(summary = "获取软件内容")
    public CommonResult<GetRespVO> get(Long id){
        return success(TechnologySoftAdminConvert.INSTANCE.convert2(softService.getById(id)));
    }

    @GetMapping("/table")
    @Operation(summary = "获取软件表单")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        PageResult<TechnologySoftDO> softDOPageResult = softService.getList(tableReqVO);

        List<TableRespVO> softDOList = new ArrayList<>( softDOPageResult.getList().size() );
        softDOPageResult.getList().forEach(item ->{
            TableRespVO tableRespVO = TechnologySoftAdminConvert.INSTANCE.convert3(item);
            tableRespVO.setTypeTitle(typeService.getById(item.getTypeId()).getTitle());
            softDOList.add( tableRespVO );
        });
        return success(new PageResult<>(softDOList,softDOPageResult.getTotal()));
    }


    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新软件状态")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        softService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新软件信息")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        softService.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除软件")
    public CommonResult<Boolean> delete(@RequestBody DeleteReqVO deleteReqVO){
        softService.delete(deleteReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "创建软件")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(softService.create(createReqVO) > 0 ? true : false);
    }

}
