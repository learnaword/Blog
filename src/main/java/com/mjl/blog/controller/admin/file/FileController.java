package com.mjl.blog.controller.admin.file;

import cn.hutool.core.util.StrUtil;
import com.mjl.blog.common.utils.ServletUtils;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.enums.FileStatusEnum;
import com.mjl.blog.service.admin.file.FileAdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileController {

    @Resource
    private FileAdminService fileService;

    @GetMapping("/upload/common/**")
    public void getCommonContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/common/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 读取内容
        FileDO fileDO = fileService.getByPath(path, FileStatusEnum.COMMON_FILE.getStatus());
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        byte[] content = fileDO.getContent();
        String fileType = fileDO.getType();
        if (content == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        //前端展示
        ServletUtils.write(response, content, fileType);
    }

    @GetMapping("/upload/blog/**")
    public void getBlogFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/blog/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }

        FileDO fileDO = fileService.getFile(path, FileStatusEnum.BLOG_FILE.getStatus());

        // 读取内容
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        //前端展示
        ServletUtils.write(response, fileDO.getContent(), fileDO.getType());

    }


    @GetMapping("/images/**")
    public void getSystemFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/images/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }

        FileDO fileDO = fileService.getFile(path, FileStatusEnum.SYSTEM_FILE.getStatus());

        // 读取内容
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        //前端展示
        ServletUtils.write(response, fileDO.getContent(), fileDO.getType());

    }

    @GetMapping("/upload/background/**")
    public void getBackgroundFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/background/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 读取内容
        FileDO fileDO = fileService.getFile(path, FileStatusEnum.BACKGROUND_FILE.getStatus());

        // 读取内容
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        //前端展示
        ServletUtils.write(response, fileDO.getContent(), fileDO.getType());
    }

    @GetMapping("/upload/js/**")
    public void getJsFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/js/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 读取内容
        FileDO fileDO = fileService.getFile(path, FileStatusEnum.JS_FILE.getStatus());

        // 读取内容
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        //前端展示
        ServletUtils.write(response, fileDO.getContent(), fileDO.getType());
    }

    @GetMapping("/upload/css/**")
    public void getCssFileContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/css/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 读取内容
        FileDO fileDO = fileService.getFile(path, FileStatusEnum.CSS_FILE.getStatus());

        // 读取内容
        if(fileDO == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        //前端展示
        ServletUtils.write(response, fileDO.getContent(), fileDO.getType());
    }

}
