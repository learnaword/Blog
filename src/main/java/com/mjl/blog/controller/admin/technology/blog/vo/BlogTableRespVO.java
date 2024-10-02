package com.mjl.blog.controller.admin.technology.blog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTableRespVO {
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;

    private String introduction;

    private String keyword;

    private Integer isTop;

    private Integer isRecommend;

    private Long updateTime;

    private Long createTime;

    private Integer status;
}
