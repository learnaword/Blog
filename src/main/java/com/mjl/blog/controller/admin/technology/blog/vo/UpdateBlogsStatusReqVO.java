package com.mjl.blog.controller.admin.technology.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateBlogsStatusReqVO {
    private List<Integer> ids;

    private int status;
}
