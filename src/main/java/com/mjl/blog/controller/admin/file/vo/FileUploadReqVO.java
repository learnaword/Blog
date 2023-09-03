package com.mjl.blog.controller.admin.file.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadReqVO {

    private MultipartFile file;
    private String path;

}
