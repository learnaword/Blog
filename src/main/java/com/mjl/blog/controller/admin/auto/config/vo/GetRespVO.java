package com.mjl.blog.controller.admin.auto.config.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRespVO {
    private Long id;

    private String title;

    private String images;

    private String contentImages;

    /*
     * 推荐html
     */
    private String recommendHtml;

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

    /*
     * 是否推荐
     */
    private Integer isRecommend;

    private Integer blogStatus;

    /*
     * 专区
     */
    private Long softId;

    /*
     * 专区板块：问答、介绍、经验
     */
    private Integer softSection;
}
