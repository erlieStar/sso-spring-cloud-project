package com.javashitang.sso.server.service.inf;

import com.javashitang.tool.OperStatus;

/**
 * @author lilimin
 * @since 2020-05-29
 */
public interface TokenService {

    OperStatus login();

    OperStatus logout();
}
