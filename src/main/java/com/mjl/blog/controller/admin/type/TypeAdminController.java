package com.mjl.blog.controller.admin.type;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.type.vo.*;
import com.mjl.blog.convert.admin.TypeAdminConvert;
import com.mjl.blog.service.admin.type.TypeAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/type")
public class TypeAdminController {
    @Resource
    private TypeAdminService typeService;

    @GetMapping("/list")
    @Operation(summary = "获取类型列表")
    public CommonResult<List<TypeListVO>> getList(){
        return CommonResult.success(TypeAdminConvert.INSTANCE.convert(typeService.getList()));
    }

    @GetMapping("/table")
    @Operation(summary = "获取类型表单")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(TypeAdminConvert.INSTANCE.convert(typeService.getList(tableReqVO)));
    }

    @GetMapping("/get")
    @Operation(summary = "类型内容")
    public CommonResult<GetRespVO> get(Long id){
        return success(TypeAdminConvert.INSTANCE.convert2(typeService.getById(id)));
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "创建类型")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(typeService.create(createReqVO) > 0 ? true : false);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新类型")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        typeService.update(updateReqVO);
        return success(true);
    }


}
