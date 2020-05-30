package com.javashitang.sso.server.service.impl;

import com.javashitang.sso.server.service.inf.TokenService;
import com.javashitang.tool.OperStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lilimin
 * @since 2020-05-29
 */
@Service
public class TokenServiceImpl implements TokenService {

    public static final String COOKIE_NAME = "loginToken";

    public static final String COOKIE_DOMAIN = "*.javashitang.com";

    @Override
    public OperStatus login(String username, String password, HttpServletResponse response) {
        return null;
    }

    @Override
    public OperStatus logout(String token) {
        return null;
    }

    @Override
    public OperStatus checkAuth(String token) {
        return null;
    }
}
