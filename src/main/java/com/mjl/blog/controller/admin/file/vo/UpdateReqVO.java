package com.mjl.blog.controller.admin.file.vo;

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
    private String name;
    @NotBlank(message="路径不能为空！！")
    private String path;

    private Integer module;

    private Integer status;

}
