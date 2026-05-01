package com.mjl.blog.framework.es.entity;

import lombok.Getter;
import lombok.Setter;
import org.dromara.easyes.annotation.IndexName;

import java.util.List;

@Getter
@Setter
@IndexName("nfturbo_collection")
public class BlogEs {
    // 博客ID
    private Long id;
    // 博客标题
    private String title;
    // 博客内容
    private String content;
    // 博客状态
    private String status;
    // 是否删除（0-未删除，1-已删除）
    private Integer deleted;
    // 博客分类
    private String category;
    // 博客标签列表
    private List<String> tags;
    // 作者
    private String author;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;
    // 浏览量
    private Integer viewCount;
    // 点赞数
    private Integer likeCount;
}
