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

    @RequestMapping("login")
    OperStatus login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("token")
    OperStatus logout(@RequestParam("token") String token);

    @RequestMapping("checkAuth")
    OperStatus checkAuth(@RequestParam("token") String token);
}
