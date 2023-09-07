package com.mjl.blog.controller.admin.media.vo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateTopsReqVO {
    private List<Integer> ids;

    private int isTop;
}
