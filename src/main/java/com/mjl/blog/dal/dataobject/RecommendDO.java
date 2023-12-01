package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName t_recommend
 */
@TableName(value ="blog_recommend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String introduction;

    private String smallTitle;

    private String images;

    private String buttonInfo;

    private String buttonBottom;

    private String buttonLink;

    private Integer status;

    private Long createTime;

    private Long updateTime;

}
