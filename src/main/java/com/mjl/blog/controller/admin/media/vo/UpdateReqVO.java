package com.mjl.blog.controller.admin.media.vo;

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

    private String introduction;

    private String keyword;

    private String images;

    /*
     * 文章内容
     */
    private String content;

    /*
     * 排名指数
     */
    private Integer rankScore;

    /*
     * 广告推荐
     */
    private Integer adType;

    /*
     * 是否置顶
     */
    private Integer isTop;

    private Long updateTime;

    private Long createTime;

    private Integer status;
}