package com.mjl.blog.controller.page.technology.blog.vo;

import lombok.Data;

@Data
public class ResultRespVO {
    private int id;
    private String title;
    private String introduction;
    private String images;
    private Long createTime;
}
