package com.mjl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileStatusEnum {
    SYSTEM_FILE(1, "系统文件"),
    COMMON_FILE(2, "普通文件"),
    BLOG_FILE(3, "博客文件"),
    BACKGROUND_FILE(4, "头像文件"),
    JS_FILE(5, "JS文件"),
    CSS_FILE(6, "CSS文件");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;
}
