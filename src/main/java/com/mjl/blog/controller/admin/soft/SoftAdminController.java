package com.mjl.blog.controller.admin.soft;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.*;
import com.mjl.blog.convert.admin.SoftAdminConvert;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.service.admin.soft.SoftAdminService;
import com.mjl.blog.service.admin.type.TypeAdminService;
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
    private SoftAdminService softService;
    @Resource
    private TypeAdminService typeService;

    @GetMapping("/softList")
    public CommonResult<List<SoftListVO>> getSoftList(){
        return CommonResult.success(SoftAdminConvert.INSTANCE.convert(softService.getSoftList()));
    }

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        PageResult<SoftDO> softDOPageResult = softService.getList(tableReqVO);

        List<TableRespVO> softDOList = new ArrayList<>( softDOPageResult.getList().size() );
        softDOPageResult.getList().forEach(item ->{
            TableRespVO tableRespVO = SoftAdminConvert.INSTANCE.convert3(item);
            tableRespVO.setTypeTitle(typeService.getById(item.getTypeId()).getTitle());
            softDOList.add( tableRespVO );
        });
        return success(new PageResult<>(softDOList,softDOPageResult.getTotal()));
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
        return success(SoftAdminConvert.INSTANCE.convert2(softService.getById(id)));
    }
}
