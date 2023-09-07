package com.mjl.blog.controller.admin.file.vo;

import com.mjl.blog.enums.FileStatusEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadReqVO {

    private MultipartFile file;
    private String path;

    private int module = FileStatusEnum.COMMON_FILE.getStatus();

}
