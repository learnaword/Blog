package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("blog_button_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonInfoDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long blogId;

    private String buttonInfo;

    private String position;

    private String ip;

    private Long createTime;

    private Long updateTime;
}
