package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName("blog_job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer status;

    private String handlerName;

    private String handlerParam;

    private String cronExpression;

    private Integer retryCount;

    private Integer retryInterval;

    private Integer monitorTimeout;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}
