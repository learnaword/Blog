package com.mjl.blog.controller.admin.recommend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReqVO {

    private Long id;

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
