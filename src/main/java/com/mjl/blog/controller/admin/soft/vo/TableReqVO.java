package com.mjl.blog.controller.admin.soft.vo;

import com.mjl.blog.common.pojo.PageParam;
import lombok.Data;

@Data
public class TableReqVO extends PageParam {
    private Integer status = 0;
}
