package com.javashitang.autoconfigure.sso;

import com.javashitang.tool.OperStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lilimin
 * @since 2020-05-30
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {

    private SsoServerClient ssoServerClient;

    private ApplicationContext applicationContext;

    public static final String LOGIN_COOKIE_NAME = "loginToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ssoServerClient = applicationContext.getBean(SsoServerClient.class);
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
                    return false;
                }
                return true;
            }
        } else {
            ResponseWrite.writeResult(response, OperStatus.newError("获取不到登陆用的cookie"));
            return false;
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
