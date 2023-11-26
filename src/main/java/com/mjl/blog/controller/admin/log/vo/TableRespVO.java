package com.mjl.blog.controller.admin.log.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO{
    private Integer id;

    private String userType;

    private String ip;

    private String description;

    private String param;

    private Long createTime;
}
