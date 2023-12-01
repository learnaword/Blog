package com.mjl.blog.controller.admin.recommend;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.recommend.vo.*;
import com.mjl.blog.convert.admin.RecommendAdminConvert;
import com.mjl.blog.dal.dataobject.RecommendDO;
import com.mjl.blog.service.admin.recommend.RecommendAdminService;
import com.mjl.blog.service.admin.type.TypeAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/recommend")
public class RecommendAdminController {

    @Resource
    private RecommendAdminService recommendService;
    @Resource
    private TypeAdminService typeService;

    @GetMapping("/list")
    @Operation(summary = "获取推荐列表")
    public CommonResult<List<RecommendListVO>> getRecommendList(){
        return CommonResult.success(RecommendAdminConvert.INSTANCE.convert(recommendService.getRecommendList()));
    }

    @GetMapping("/get")
    @Operation(summary = "获取推荐内容")
    public CommonResult<GetRespVO> get(Long id){
        return success(RecommendAdminConvert.INSTANCE.convert2(recommendService.getById(id)));
    }

    @GetMapping("/table")
    @Operation(summary = "获取推荐表单")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        PageResult<RecommendDO> recommendDOPageResult = recommendService.getList(tableReqVO);
        return success(RecommendAdminConvert.INSTANCE.convert(recommendDOPageResult));
    }


    @PutMapping("/update-status")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新推荐状态")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        recommendService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新推荐信息")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        recommendService.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除推荐")
    public CommonResult<Boolean> delete(@RequestBody DeleteReqVO deleteReqVO){
        recommendService.delete(deleteReqVO);
        return success(true);
    }

    @PostMapping ("/create")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "创建推荐")
    public CommonResult<Boolean> create(@RequestBody @Valid CreateReqVO createReqVO){
        return success(recommendService.create(createReqVO) > 0 ? true : false);
    }

}
