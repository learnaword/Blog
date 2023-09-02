package com.mjl.blog.framework.security.core;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {
    private Long id;
    private boolean hasPermission;

}
