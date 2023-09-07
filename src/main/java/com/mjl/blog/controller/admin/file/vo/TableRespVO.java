package com.mjl.blog.controller.admin.file.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO {
    private Long id;
    /**
     * 原文件名
     */
    private String name;
    /**
     * 路径，即文件名
     */
    private String path;

    private Integer module;

    /**
     * 访问地址
     */
    private String url;
    /**
     * 文件的 MIME 类型，例如 "application/octet-stream"
     */
    private String type;
    /**
     * 文件大小
     */
    private Integer size;


    private Integer status;

    private Long createTime;

    private Long UpdateTime;

}
