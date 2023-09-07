package com.mjl.blog.controller.admin.file.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateModuleReqVO {
    private List<Long> ids;

    private int module;
}
