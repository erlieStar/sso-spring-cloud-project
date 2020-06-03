package com.javashitang.sso.server.controller;

import com.javashitang.sso.server.service.inf.TokenService;
import com.javashitang.tool.OperStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public OperStatus login(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpServletResponse response) {
        if (StringUtils.isBlank(username)) {
            return OperStatus.newParamInvalid("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return OperStatus.newParamInvalid("密码不能为空");
        }
        return tokenService.login(username, password, response);
    }

    @RequestMapping("logout")
    public OperStatus logout(@RequestParam("token") String token, HttpServletResponse response) {
        if (StringUtils.isBlank(token)) {
            return OperStatus.newParamInvalid("token不能为空");
        }
        return tokenService.logout(token, response);
    }

    /**
     * 通过token验证用户权限
     */
    @RequestMapping("checkAuth")
    public OperStatus checkAuth(@RequestParam("token") String token) {
        if (StringUtils.isBlank(token)) {
            return OperStatus.newParamInvalid("token不能为空");
        }
        return tokenService.checkAuth(token);
    }

}
