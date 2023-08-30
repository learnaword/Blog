package com.mjl.blog.controller.admin.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqVO {
    private String username;
    private String password;
}
