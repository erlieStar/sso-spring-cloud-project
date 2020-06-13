package com.javashitang.sso.sample.controller;

import com.javashitang.autoconfigure.sso.ParseUserInfo;
import com.javashitang.autoconfigure.sso.UserInfo;
import com.javashitang.tool.OperStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("location")
public class LocationController {

    @RequestMapping("getInfo")
    public OperStatus getInfo(HttpServletRequest request) {
        // 在各个系统中如果想获取登陆的用户信息，可以调用这个工具类方法
        // 因为在校验的时候，将用户信息设置到request中了
        UserInfo userInfo = ParseUserInfo.parseFromRequest(request);
        log.info("login userId: {}, username: {}", userInfo.getId(), userInfo.getUsername());
        return OperStatus.newSuccess("获取管理信息");
    }
}
