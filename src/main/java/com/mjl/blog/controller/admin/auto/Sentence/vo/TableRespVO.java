package com.mjl.blog.controller.admin.auto.Sentence.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO {
    private Long id;

    private String content;

    private Integer usages;

    private Long createTime;

    private Long updateTime;

    private Integer status;

}
