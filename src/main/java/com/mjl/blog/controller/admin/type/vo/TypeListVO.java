package com.mjl.blog.controller.admin.type.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeListVO {
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private Long updatetime;

    private Long createtime;

    private Integer status;

}
