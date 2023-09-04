package com.mjl.blog.controller.admin.auto.Sentence.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReqVO {

    private Long id;

    private String content;

    private Integer usages;

    private Integer status;
}
