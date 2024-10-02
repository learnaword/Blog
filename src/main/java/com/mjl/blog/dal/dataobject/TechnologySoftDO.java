package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName technology_soft
 */
@TableName(value ="technology_soft")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologySoftDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String introduction;

    private String images;

    private Long updateTime;

    private Long createTime;

    private Long typeId;

    private Integer status;

    private String token;
}
