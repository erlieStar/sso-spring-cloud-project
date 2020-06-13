package com.javashitang.sso.server.service.impl;

import com.javashitang.sso.server.dao.UserInfoMapper;
import com.javashitang.sso.server.po.UserInfo;
import com.javashitang.sso.server.service.inf.TokenService;
import com.javashitang.tool.MD5Util;
import com.javashitang.tool.OperStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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
    public static final Integer TOKEN_EXPIRE_HOUR = 8;

    @Value("${cookie.domain}")
    private String cookieDomain;

    /**
     * token可以直接设置到cookie中，也可以通过json放回
     * 前端可以通过cookie，header，cookie等多种方式将token带回来
     */
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
        String token = this.genToken(username);
        UserInfo update = new UserInfo();
        update.setId(userInfo.getId());
        update.setToken(token);
        update.setTokenExpire(LocalDateTime.now().plusHours(TOKEN_EXPIRE_HOUR));
        userInfoMapper.updateByPrimaryKeySelective(update);
        this.setCookie(TOKEN_EXPIRE_HOUR * 1000, token, response);
        return OperStatus.newSuccess();
    }

    @Override
    public OperStatus logout(String token, HttpServletResponse response) {
        log.info("logout param token: {}", token);
        UserInfo userInfo = userInfoMapper.selectByToken(token);
        if (userInfo == null) {
            return OperStatus.newError("无法找到用户");
        }
        UserInfo update = new UserInfo();
        update.setId(userInfo.getId());
        update.setToken("");
        update.setTokenExpire(LocalDateTime.now());
        userInfoMapper.updateByPrimaryKeySelective(update);
        this.setCookie(1, "expire_now", response);
        return OperStatus.newSuccess();
    }

    /**
     * 通过token校验用户是否登陆这一步，可以加上缓存，将用户信息放到redis中，这里就不再演示了
     */
    @Override
    public OperStatus checkAuth(String token) {
        log.info("checkAuth param token: {}", token);
        UserInfo userInfo = userInfoMapper.selectByToken(token);
        if (userInfo == null || userInfo.getTokenExpire().isBefore(LocalDateTime.now())) {
            return OperStatus.newError("校验失败");
        }
        return OperStatus.newSuccess(userInfo);
    }

    private String genToken(String username) {
        return MD5Util.getMd5Str(new StringBuilder().append(username)
                .append(System.currentTimeMillis()).append(new Random().nextInt()).toString());
    }

    private void setCookie(Integer maxAge, String token, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(cookieDomain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
