package com.mjl.blog.controller.admin.job.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReqVO {

    private Long id;

    private String name;

    private Integer status;

    private String handlerName;

    private String handlerParam;

    private String cronExpression;

    private Integer retryCount;

    private Integer retryInterval;

    private Integer monitorTimeout;

    private Long createTime;

    private Long updateTime;

    private Boolean deleted;

}
