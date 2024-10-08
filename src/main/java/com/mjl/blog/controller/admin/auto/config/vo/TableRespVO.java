package com.mjl.blog.controller.admin.auto.config.vo;

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

    private String softTitle;

    private Integer softSection;

    private Integer isTop;

    private Integer isRecommend;

    private Integer status;

    private Integer blogStatus;

}
