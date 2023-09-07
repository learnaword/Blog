package com.mjl.blog.controller.admin.type.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO {
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;

    private Integer status;

    private Long createTime;

    private Long updateTime;

}
