package com.mjl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 *
 */
@Getter
@AllArgsConstructor
public enum BlogStatusEnum {

    PUBLISHED(1, "已发布"),
    SPAM(2, "垃圾"),
    RESERVED(-1, "储备"),
    DRAFT(-2, "草稿");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

}
