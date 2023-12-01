package com.mjl.blog.controller.admin.file.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRespVO {
    private Long id;

    private String name;

    @NotBlank(message = "路径不能为空")
    private String path;

    private String url;

    private Integer module;

    private Integer status;

}
