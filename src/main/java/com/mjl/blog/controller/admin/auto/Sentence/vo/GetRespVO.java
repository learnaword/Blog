package com.mjl.blog.controller.admin.auto.Sentence.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRespVO {
    private Long id;

    private String content;

    private Integer usages;

    private Integer status;
}
