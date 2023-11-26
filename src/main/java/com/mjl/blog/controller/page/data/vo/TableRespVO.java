package com.mjl.blog.controller.page.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO{
    private Long id;
    private String blogTitle;
    private Long blogId;
    private String buttonInfo;
    private String createTime;
    private String ip;
    private String position;
}
