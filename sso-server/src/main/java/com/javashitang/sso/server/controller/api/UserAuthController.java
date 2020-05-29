package com.javashitang.sso.server.controller.api;

import com.javashitang.sso.server.service.inf.TokenService;
import com.javashitang.tool.OperStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lilimin
 * @Date: 2019/6/10 21:57
 */
@RestController
@RequestMapping("api/auth")
public class UserAuthController {

    @Resource
    private TokenService tokenService;

    @RequestMapping("login")
    public OperStatus login(HttpServletResponse response) {
        return tokenService.login();
    }

    @RequestMapping("logout")
    public OperStatus cart() {
        return tokenService.logout();
    }

}
