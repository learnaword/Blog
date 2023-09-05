package com.mjl.blog.controller.admin.auto.blog.vo;

import lombok.Data;

@Data
public class CreateReqVO {
    private String title;
    private Long autoConfig;
    private String content;
    private String images;

}
