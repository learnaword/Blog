package com.mjl.blog.controller.page.blog.vo;

import lombok.Data;

@Data
public class NewListRespVO {
    private int id;
    private String title;
    private String introduction;
    private String images;
    private String createTime;
}
