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

    //===========文件管理1002003000====================
    ErrorCode PATH_OCCUPIED = new ErrorCode(1002003001, "path已经被占用！");
    ErrorCode FILE_TYPE_MATCH = new ErrorCode(1002003002, "文件类型不匹配！");
    ErrorCode FILE_NOT_FOUND = new ErrorCode(1002003002, "文件不存在！");

    //===========权限异常===============================
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");

    // ========== 定时任务 1-001-001-000 ==========
    ErrorCode JOB_NOT_EXISTS = new ErrorCode(1_001_001_000, "定时任务不存在");
    ErrorCode JOB_HANDLER_EXISTS = new ErrorCode(1_001_001_001, "定时任务的处理器已经存在");
    ErrorCode JOB_CHANGE_STATUS_INVALID = new ErrorCode(1_001_001_002, "只允许修改为开启或者关闭状态");
    ErrorCode JOB_CHANGE_STATUS_EQUALS = new ErrorCode(1_001_001_003, "定时任务已经处于该状态，无需修改");
    ErrorCode JOB_UPDATE_ONLY_NORMAL_STATUS = new ErrorCode(1_001_001_004, "只有开启状态的任务，才可以修改");
    ErrorCode JOB_CRON_EXPRESSION_VALID = new ErrorCode(1_001_001_005, "CRON 表达式不正确");



}
