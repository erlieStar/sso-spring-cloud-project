package com.javashitang.autoconfigure.sso;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ParseUserInfo {

    private static String USER_ATTRIBUTE_NAME = "userInfo";

    public static UserInfo parseFromRequest(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(USER_ATTRIBUTE_NAME);
        // 不会发生，但还是判断一下
        if (userInfo == null) {
            log.error("failed get userInfo from request");
        }
        return userInfo;
    }

    public static void setToRequest(HttpServletRequest request, UserInfo userInfo) {
        request.setAttribute(USER_ATTRIBUTE_NAME, userInfo);
    }
}
