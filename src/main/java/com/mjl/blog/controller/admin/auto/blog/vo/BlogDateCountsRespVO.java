package com.mjl.blog.controller.admin.auto.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDateCountsRespVO {

    private ArrayList<Long> BlogDateCounts;

}
