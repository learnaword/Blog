package com.mjl.blog.controller.admin.recommend.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRespVO {
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String introduction;

    private String smallTitle;

    private String images;

    private String buttonInfo;

    private String buttonBottom;

    private String buttonLink;

    private Integer status;

    private Long createTime;

    private Long updateTime;

}
