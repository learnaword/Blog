package com.mjl.blog.controller.admin.auto.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTypeCountsRespVO {
    //发布的数量，status=1
    private Long publishedCounts;
    //垃圾箱数量，status=2
    private Long spmCounts;
    //储备数量，status=-1
    private Long resercedCounts;
    //草稿数量，status-2
    private Long draftCounts;
    //总数量
    private Long allCounts;

    public void initAllCounts(){
        allCounts = draftCounts + resercedCounts + spmCounts + publishedCounts;
    }
}
