package com.mjl.blog.controller.admin.media.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateCountsRespVO {

    private ArrayList<Long> dateCounts;

}
