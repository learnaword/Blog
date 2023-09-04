package com.mjl.blog.controller.admin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTableRespVO {
    private Long id;

    private String title;

    private String introduction;

    private String keyword;

    private Integer isTop;

    private Integer isRecommend;

    private Long updateTime;

    private Long createTime;

    private Integer status;
}
