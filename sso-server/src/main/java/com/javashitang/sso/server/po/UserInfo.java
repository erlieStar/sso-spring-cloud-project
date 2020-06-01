package com.javashitang.sso.server.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private String token;

    private LocalDateTime tokenExpire;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}