package com.mjl.blog.controller.admin.soft.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReqVO {

    @NotBlank(message="标题不能为空！！")
    private String title;

    private String images;

    private String contentImages;

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

    /*
     * 通过该模版生成文章的状态
     */
    private Integer blogStatus;

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
