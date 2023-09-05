package com.mjl.blog.controller.admin.auto.Sentence.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FIleCreateReqVO {
    private MultipartFile file;
    private String path;
}
