package com.mjl.blog.controller.admin.blog.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRespVO {
    private Long id;
    @NotBlank(message = "标题不能为空")
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
    private List<Long> adTypes;

    /*
     * 是否置顶
     */
    private Integer isTop;

    /*
     * 是否推荐
     */
    private Integer isRecommend;

    private Long updateTime;

    private Long createTime;

    private Integer status;

    /*
     * 专区
     */
    private Long softId;

    /*
     * 专区板块：问答、介绍、经验
     */
    private Integer softSection;
}
