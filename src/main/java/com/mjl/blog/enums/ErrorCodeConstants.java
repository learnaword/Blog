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

    //===========文章自动生成1002001000================
    ErrorCode INVALID_TEMPLATE = new ErrorCode(1002001001, "不存在可用模版");

    //===========文件管理1002003000================
    ErrorCode PATH_OCCUPIED = new ErrorCode(1002003001, "path已经被占用！");
    ErrorCode FILE_TYPE_MATCH = new ErrorCode(1002003002, "文件类型不匹配！");
    ErrorCode FILE_NOT_FOUND = new ErrorCode(1002003002, "文件不存在！");



}
