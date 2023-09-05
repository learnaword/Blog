package com.mjl.blog.controller.admin.auto.Sentence;

import cn.hutool.core.io.IoUtil;
import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.*;
import com.mjl.blog.service.admin.auto.sentence.SentenceService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import com.mjl.blog.convert.SentenceConvert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/auto-sentence")
public class SentenceController {
    @Resource
    private SentenceService sentenceService;

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

    @RequestMapping ("/fileCreate")
    public CommonResult<Boolean> fileCreate(FIleCreateReqVO fIleCreateReqVO) throws IOException {
        MultipartFile file = fIleCreateReqVO.getFile();
        String path = fIleCreateReqVO.getPath();
        String name = file.getOriginalFilename();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        sentenceService.fileCreate(content,path,name);
        return success(true);
    }

}
