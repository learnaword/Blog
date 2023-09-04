package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName t_auto_config
 */
@TableName(value ="t_auto_config")
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

    private Integer adType;

    private Integer softSection;

    private Integer isTop;

    private Integer isRecommend;

    private Long softId;

    private Integer blogStatus;

    private Integer status;

    private Long createTime;

    private Long updateTime;
}
