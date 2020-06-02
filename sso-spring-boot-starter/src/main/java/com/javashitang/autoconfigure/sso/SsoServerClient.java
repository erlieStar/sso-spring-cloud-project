package com.javashitang.autoconfigure.sso;

import com.javashitang.tool.OperStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lilimin
 * @since 2020-05-30
 */
@FeignClient(name = "SSO-SERVER", fallbackFactory = SsoServerClientFactory.class)
public interface SsoServerClient {

    @RequestMapping("api/auth/login")
    OperStatus login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("api/auth/logout")
    OperStatus logout(@RequestParam("token") String token);

    @RequestMapping("api/auth/checkAuth")
    OperStatus checkAuth(@RequestParam("token") String token);
}
