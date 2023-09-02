package com.mjl.blog.controller.admin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDateCountsRespVO {

    private List<Long> BlogDateCounts;

}
