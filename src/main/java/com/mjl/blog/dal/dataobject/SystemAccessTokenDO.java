package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName system_access_token
 */
@TableName(value ="system_access_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemAccessTokenDO implements Serializable {
    private Integer id;

    private String accessToken;

    private String refreshToken;

    private Long userId;

    private LocalDateTime expires;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
