package com.mjl.blog.controller.admin.soft.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftListVO {
    private Long id;

    private String title;

    private String introduction;

    private String images;

    private Long updatetime;

    private Long createtime;

    private Integer typeId;

    private Integer status;

    private String token;
}
