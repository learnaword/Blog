package com.mjl.blog.dal.redis;


import com.mjl.blog.framework.redis.core.RedisKeyDefine;

public interface RedisKeyConstants {
    RedisKeyDefine BLOG_INFO_HOT_LIST = new RedisKeyDefine("热门文章",
            "BLOG_INFO_HOT_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine BLOG_INFO_REL_LIST = new RedisKeyDefine("相关文章",
            "BLOG_INFO_REL_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine BLOG_INFO_TOP_LIST = new RedisKeyDefine("置顶文章",
            "BLOG_INFO_TOP_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine MEDIA_INFO_HOT_LIST = new RedisKeyDefine("热门微头条",
            "MEDIA_INFO_HOT_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine MEDIA_INFO_REL_LIST = new RedisKeyDefine("相关微头条",
            "MEDIA_INFO_REL_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine MEDIA_INFO_TOP_LIST = new RedisKeyDefine("置顶微头条",
            "MEDIA_INFO_TOP_LIST", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine TECHNOLOGY_INFO_HOT_LIST = new RedisKeyDefine("热门文章",
            "TECHNOLOGY_INFO_HOT_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine TECHNOLOGY_INFO_REL_LIST = new RedisKeyDefine("相关文章",
            "TECHNOLOGY_INFO_REL_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
    RedisKeyDefine TECHNOLOGY_INFO_TOP_LIST = new RedisKeyDefine("置顶文章",
            "TECHNOLOGY_INFO_TOP_LIST:%d", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.LIST, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
}
