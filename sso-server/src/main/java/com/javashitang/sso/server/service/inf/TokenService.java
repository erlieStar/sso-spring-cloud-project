package com.javashitang.sso.server.service.inf;

import com.javashitang.tool.OperStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lilimin
 * @since 2020-05-29
 */
public interface TokenService {

    OperStatus login(String username, String password, HttpServletResponse response);

    OperStatus logout(String token, HttpServletResponse response);

    OperStatus checkAuth(String token);
}
