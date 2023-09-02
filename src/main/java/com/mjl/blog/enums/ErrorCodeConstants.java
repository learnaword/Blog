package com.mjl.blog.enums;

import com.mjl.blog.common.exception.ErrorCode;

/*
*错误码
*
* code：1-001-000-000
*
 */
public interface ErrorCodeConstants {

    //===========登录模块1001001000================
    ErrorCode USERNAME_NOT_FOUND = new ErrorCode(1001001001, "用户名不存在");
    ErrorCode PASSWORD_ERROR = new ErrorCode(1001001002, "密码错误");

}