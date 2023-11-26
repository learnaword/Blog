package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_blog
 */
@TableName(value ="b_blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDO implements Serializable{

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
    * 广告推荐
     */
    private Integer adType;

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
