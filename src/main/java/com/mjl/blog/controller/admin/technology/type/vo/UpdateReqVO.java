package com.mjl.blog.controller.admin.technology.type.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReqVO {

    private Long id;
    @NotBlank(message="标题不能为空！！")
    private String title;

    private Integer status;
}
