package com.mjl.blog.controller.admin.technology.soft.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateStatusReqVO {
    private List<Integer> ids;

    private int status;
}