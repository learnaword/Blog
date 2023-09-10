package com.mjl.blog.dal.redis;


import com.mjl.blog.framework.redis.core.RedisKeyDefine;

public interface RedisKeyFileConstants {
    RedisKeyDefine UPLOAD_BLOG_IMAGES = new RedisKeyDefine("系统图片",
            "BLOG_UPLOAD_IMAGES:%d:%s", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.STRING, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
}
