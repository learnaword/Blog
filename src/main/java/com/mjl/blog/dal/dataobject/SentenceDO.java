package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_sentence
 */
@TableName(value ="t_sentence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentenceDO implements Serializable {
    private Long id;

    private String content;

    private Integer usages;

    private Long createTime;

    private Long updateTime;

    private Integer status;
}
