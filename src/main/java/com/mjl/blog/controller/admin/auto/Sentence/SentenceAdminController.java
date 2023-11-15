package com.mjl.blog.controller.admin.auto.Sentence;

import cn.hutool.core.io.IoUtil;
import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.*;
import com.mjl.blog.convert.admin.SentenceAdminConvert;
import com.mjl.blog.service.admin.auto.sentence.SentenceAdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/auto-sentence")
public class SentenceAdminController {
    @Resource
    private SentenceAdminService sentenceService;

    @GetMapping("/table")
    @Operation(summary = "获取句子表单")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(SentenceAdminConvert.INSTANCE.convert(sentenceService.getList(tableReqVO)));
    }

    @GetMapping("/get")
    @Operation(summary = "获取句子信息根据ID")
    public CommonResult<GetRespVO> get(Long id){
        return success(SentenceAdminConvert.INSTANCE.convert2(sentenceService.getById(id)));
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新句子状态")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        sentenceService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PutMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "更新句子信息")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        sentenceService.update(updateReqVO);
        return success(true);
    }

    @PostMapping ("/create-file")
    @PreAuthorize("@ss.hasPermissions()")
    @Operation(summary = "上传文件信息")
    public CommonResult<Boolean> fileCreate(FIleCreateReqVO fIleCreateReqVO) throws IOException {
        MultipartFile file = fIleCreateReqVO.getFile();
        String path = fIleCreateReqVO.getPath();
        String name = file.getOriginalFilename();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        sentenceService.fileCreate(content,path,name);
        return success(true);
    }

}
