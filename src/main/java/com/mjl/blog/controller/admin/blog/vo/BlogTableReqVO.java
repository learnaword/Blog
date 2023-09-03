package com.mjl.blog.controller.admin.blog.vo;

import com.mjl.blog.common.pojo.PageParam;
import lombok.Data;

@Data
public class BlogTableReqVO extends PageParam {
    private Integer status = 1;
}
