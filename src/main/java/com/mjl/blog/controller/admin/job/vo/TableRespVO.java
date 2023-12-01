package com.mjl.blog.controller.admin.job.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRespVO {
    private Long id;

    @NotBlank(message = "标题不能为空")
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
