package com.mjl.blog.controller.admin.technology.soft.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReqVO {

    @NotBlank(message="标题不能为空！！")
    private String title;

    private Integer status;

    private String introduction;

    private String images;

    private String token;

    private Long typeId;
}
