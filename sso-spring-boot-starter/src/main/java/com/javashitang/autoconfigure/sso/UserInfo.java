package com.javashitang.autoconfigure.sso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lilimin
 * @since 2020-05-30
 * 用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer id;

    private String username;

    private String token;
}