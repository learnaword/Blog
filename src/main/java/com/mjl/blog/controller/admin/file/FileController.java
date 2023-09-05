package com.mjl.blog.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.mjl.blog.common.pojo.CommonResult;
import com.mjl.blog.common.utils.ServletUtils;
import com.mjl.blog.controller.admin.file.vo.FileUploadReqVO;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.service.admin.file.FileService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping({"/admin/file/upload"})
    public CommonResult<String> upload(FileUploadReqVO fileUploadReqVO) throws IOException {
        MultipartFile file = fileUploadReqVO.getFile();
        String path = fileUploadReqVO.getPath();
        String name = file.getOriginalFilename();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        return CommonResult.success(fileService.upload(name,path,content));
    }

    @GetMapping("/admin-api/infra/file/get/**")
    public void getFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 读取内容
        FileDO fileDO = fileService.getByPath(path);
        byte[] content = fileDO.getContent();
        String fileType = fileDO.getType();
        if (content == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        //前端展示
        ServletUtils.write(response, content, fileType);
    }

}
