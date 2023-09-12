package com.mjl.blog.dal.redis;


import com.mjl.blog.framework.redis.core.RedisKeyDefine;

public interface RedisLogConstants {
    RedisKeyDefine LOG_IP = new RedisKeyDefine("访问ip",
            "LOG_IP:%s", // 参数来自 DefaultLockKeyBuilder 类
            RedisKeyDefine.KeyTypeEnum.STRING, Integer.class , RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);
}
