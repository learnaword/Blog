package com.mjl.blog.controller.admin.auto.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateBlogsRecommendReqVO {
    private List<Integer> ids;

    private int isRecommend;
}
