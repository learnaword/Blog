package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_blog
 */
@TableName(value ="t_blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDO implements Serializable{
    private Integer id;

    private String title;

    private String introduction;

    private String keyword;

    private String images;

    private String content;

    private Integer clicknum;

    private Integer commentnum;

    private Integer agreenum;

    private Integer istop;

    private Integer isrecommend;

    private Long updatetime;

    private Long createtime;

    private Integer status;

    private Integer typeId;

    private Integer softId;
}
