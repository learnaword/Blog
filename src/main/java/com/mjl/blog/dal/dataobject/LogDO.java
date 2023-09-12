package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("blog_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userType;

    private String ip;

    private String description;

    private String param;

    private Long createTime;
}
