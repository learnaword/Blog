package com.mjl.blog.controller.admin.auto.Sentence;

import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.*;
import com.mjl.blog.service.admin.auto.sentence.SentenceService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import com.mjl.blog.convert.SentenceConvert;
import org.springframework.web.bind.annotation.*;


import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/auto-sentence")
public class SentenceController {
    @Resource
    SentenceService sentenceService;

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(SentenceConvert.INSTANCE.convert(sentenceService.getList(tableReqVO)));
    }

    @PostMapping("/updateStatus")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        sentenceService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<GetRespVO> get(Long id){
        return success(SentenceConvert.INSTANCE.convert2(sentenceService.getById(id)));
    }


    @PostMapping ("/update")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        sentenceService.update(updateReqVO);
        return success(true);
    }


}
