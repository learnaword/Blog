package com.mjl.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_bloger")
public class AdminUserDO implements Serializable {
    private Long id;
    private String username;
    private String password;
}
