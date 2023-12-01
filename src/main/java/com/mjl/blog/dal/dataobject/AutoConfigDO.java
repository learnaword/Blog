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
 * @TableName t_auto_config
 */
@TableName(value ="blog_auto_config",autoResultMap = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoConfigDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String images;

    private String contentImages;

    private String recommendHtml;

    private Integer rankScore;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> adTypes;

    private Integer softSection;

    private Integer isTop;

    private Integer isRecommend;

    private Long softId;

    private Integer blogStatus;

    private Integer status;

    private Long createTime;

    private Long updateTime;
}
