package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("t_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDO implements Serializable {
    /**
     * 编号，数据库自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 原文件名
     */
    private String name;
    /**
     * 路径，即文件名
     */
    private String path;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 文件的 MIME 类型，例如 "application/octet-stream"
     */
    private String type;

    private Integer module;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 文件内容
     */
    private byte[] content;

    private Integer status;

    private Long createTime;

    private Long UpdateTime;
}
