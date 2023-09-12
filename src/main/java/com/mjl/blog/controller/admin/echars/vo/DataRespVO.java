package com.mjl.blog.controller.admin.echars.vo;

import lombok.Data;

@Data
public class DataRespVO {

    private String[] days;
    private Long[] counts;
    private Long total;
}
