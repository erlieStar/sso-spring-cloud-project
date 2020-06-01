package com.javashitang.sso.server.service.impl;

import com.javashitang.sso.server.dao.UserInfoMapper;
import com.javashitang.sso.server.po.UserInfo;
import com.javashitang.sso.server.service.inf.TokenService;
import com.javashitang.tool.MD5Util;
import com.javashitang.tool.OperStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @author lilimin
 * @since 2020-05-29
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    private UserInfoMapper userInfoMapper;

    public static final String COOKIE_NAME = "loginToken";
    public static final String COOKIE_DOMAIN = "*.javashitang.com";
    public static final Integer TOKEN_EXPIRE_HOUR = 8;

    @Override
    public OperStatus login(String username, String password, HttpServletResponse response) {
        log.info("login param username: {}", username);
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            return OperStatus.newError("用户名或密码错误");
        }
        if (!userInfo.getPassword().equals(MD5Util.getMd5Str(password))) {
            return OperStatus.newError("用户名或密码错误");
        }
        String token = null;
        this.setCookie(TOKEN_EXPIRE_HOUR * 1000, token, response);
        return OperStatus.newSuccess();
    }

    @Override
    public OperStatus logout(String token) {
        log.info("logout param token: {}", token);
        UserInfo userInfo = userInfoMapper.selectByToken(token);
        if (userInfo == null) {
            return OperStatus.newError("无法找到用户");
        }
        UserInfo update = new UserInfo();
        update.setId(userInfo.getId());
        update.setToken("");
        return OperStatus.newSuccess();
    }

    /**
     * 通过token校验用户是否登陆这一步，可以加上缓存，将用户信息放到redis中，这里就不再演示了
     */
    @Override
    public OperStatus checkAuth(String token) {
        log.info("checkAuth param token: {}", token);
        UserInfo userInfo = userInfoMapper.selectByToken(token);
        if (userInfo == null) {
            return OperStatus.newError("token错误");
        }
        return OperStatus.newSuccess();
    }

    private String genToken(String username) {
        return MD5Util.getMd5Str(new StringBuilder().append(username)
                .append(System.currentTimeMillis()).append(new Random().nextInt()).toString());
    }

    private void setCookie(Integer maxAge, String token, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
