package com.mjl.blog.controller.page.blog.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mjl.blog.dal.dataobject.SoftDO;
import lombok.Data;

@Data
public class BlogInfoRespVO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String introduction;

    private String keyword;

    /*
     * 文章内容
     */
    private String content;

    /*
     * 广告推荐
     */
    private Integer adType;

    private String createTime;

    /*
     * 专区
     */
    private SoftDO soft;

    /*
     * 专区板块：问答、介绍、经验
     */
}
