package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @TableName technology_blog
 */
@TableName(value ="technology_blog",autoResultMap = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyBlogDO implements Serializable{

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String introduction;

    private String keyword;

    private String images;

    /*
    * 文章内容
     */
    private String content;

    /*
    * 排名指数
     */
    private Integer rankScore;

    /*
    * 是否置顶
     */
    private Integer isTop;

    /*
    * 是否推荐
     */
    private Integer isRecommend;

    private Long updateTime;

    private Long createTime;

    private Integer status;

    /*
    * 专区
     */
    private Long softId;

    /*
    * 专区板块：问答、介绍、经验
    */
    private Integer softSection;

}
