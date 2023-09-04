package com.mjl.blog.controller.admin.auto.config.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO {
    private Long id;

    private String title;

    private String softTitle;

    private Integer softSection;

    private Integer isTop;

    private Integer isRecommend;

    private Integer status;

    private Integer blogStatus;

}
