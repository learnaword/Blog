package com.mjl.blog.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.ServletUtils;
import com.mjl.blog.controller.admin.file.vo.*;
import com.mjl.blog.convert.admin.FileAdminConvert;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.enums.FileStatusEnum;
import com.mjl.blog.service.admin.file.FileAdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mjl.blog.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/file")
public class FileAdminController {

    @Resource
    private FileAdminService fileService;

    @RequestMapping({"/upload"})
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<String> upload(FileUploadReqVO fileUploadReqVO) throws IOException {
        MultipartFile file = fileUploadReqVO.getFile();
        String path = fileUploadReqVO.getPath();
        String name = file.getOriginalFilename();
        int module = fileUploadReqVO.getModule();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        return CommonResult.success(fileService.upload(name,path,content, module));
    }

    @GetMapping("/table")
    public CommonResult<PageResult<TableRespVO>> getTable(TableReqVO tableReqVO){
        return success(FileAdminConvert.INSTANCE.convert(fileService.getList(tableReqVO)));
    }

    @PostMapping("/updateStatus")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> updateStatus(@RequestBody UpdateStatusReqVO updateStatusReqVO){
        fileService.updateStatus(updateStatusReqVO);
        return success(true);
    }

    @PostMapping ("/updateModule")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> updateModule(@RequestBody UpdateModuleReqVO updateModuleReqVO){
        fileService.updateModule(updateModuleReqVO);
        return success(true);
    }

    @GetMapping("/get")
    public CommonResult<GetRespVO> get(Long id){
        return success(FileAdminConvert.INSTANCE.convert2(fileService.getById(id)));
    }

    @PostMapping ("/update")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> update(@RequestBody @Valid UpdateReqVO updateReqVO){
        fileService.update(updateReqVO);
        return success(true);
    }

    @RequestMapping ("/updateContent")
    @PreAuthorize("@ss.hasPermissions()")
    public CommonResult<Boolean> updateContent(UpdateContentReqVO updateContentReqVO) throws IOException {
        MultipartFile file = updateContentReqVO.getFile();
        String path = updateContentReqVO.getPath();
        String name = file.getOriginalFilename();
        Long id = updateContentReqVO.getId();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        fileService.updateContent(name,path,content,id);
        return success(true);
    }

}
