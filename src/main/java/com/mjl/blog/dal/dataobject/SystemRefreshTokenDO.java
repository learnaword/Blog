package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName system_refresh_token
 */
@TableName(value ="system_refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemRefreshTokenDO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String refreshToken;

    private Long userId;

    private LocalDateTime expires;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
