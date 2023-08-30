package com.mjl.blog.controller.admin.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRespVO {

    private Long userId;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiresTime;
}
