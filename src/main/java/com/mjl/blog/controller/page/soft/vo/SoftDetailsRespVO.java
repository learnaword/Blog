package com.mjl.blog.controller.page.soft.vo;

import lombok.Data;

@Data
public class SoftDetailsRespVO {
    private int id;
    private String title;
    private String introduction;
    private String images;

    private String createTime;
}
