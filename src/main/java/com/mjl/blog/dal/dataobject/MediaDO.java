package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName t_blog
 */
@TableName(value ="t_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDO implements Serializable{
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

    private Long updateTime;

    private Long createTime;

    private Integer status;

}
