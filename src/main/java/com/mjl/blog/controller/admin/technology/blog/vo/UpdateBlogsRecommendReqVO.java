package com.mjl.blog.controller.admin.technology.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateBlogsRecommendReqVO {
    private List<Integer> ids;

    private int isRecommend;
}