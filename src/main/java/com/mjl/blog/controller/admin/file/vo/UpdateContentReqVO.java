package com.mjl.blog.controller.admin.file.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateContentReqVO {

    private Long id;
    private MultipartFile file;
    private String path;
}
