package com.mjl.blog.controller.admin.auto.blog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReqVO {

    @NotBlank(message = "标题不能为空")
    private String title;
    private Long autoConfig;
    private String content;
    private String images;

}
