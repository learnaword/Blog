package com.mjl.blog.framework.es.entity;

import lombok.Getter;
import lombok.Setter;
import org.dromara.easyes.annotation.IndexName;

import java.util.List;

@Getter
@Setter
@IndexName("nfturbo_collection")
public class BlogEs {
    private Long id;
    private String title;
    private String content;
    private String status;
    private Integer deleted;
    private String category;
    private List<String> tags;
    private String author;
    private Long createTime;
    private Long updateTime;
    private Integer viewCount;
    private Integer likeCount;
}
