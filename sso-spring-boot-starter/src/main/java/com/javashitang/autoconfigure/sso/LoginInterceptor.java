package com.javashitang.autoconfigure.sso;

import com.javashitang.tool.OperStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lilimin
 * @since 2020-05-30
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private SsoServerClient ssoServerClient;

    public static final String LOGIN_COOKIE_NAME = "loginToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            String tokenValue = null;
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (LOGIN_COOKIE_NAME.equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                    break;
                }
            }
            if (StringUtils.isEmpty(tokenValue)) {
                ResponseWrite.writeResult(response, OperStatus.newError("获取不到登陆用的cookie"));
            } else {
                // 这里的result可以放用户的信息
                OperStatus result = ssoServerClient.checkAuth(tokenValue);
                if (!result.isSuccess()) {
                    ResponseWrite.writeResult(response, result);
                }
                return true;
            }
        } else {
            ResponseWrite.writeResult(response, OperStatus.newError("获取不到登陆用的cookie"));
            return false;
        }
        return false;
    }
}
