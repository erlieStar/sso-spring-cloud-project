package com.javashitang.sso.server.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lilimin
 * @since 2020-05-29
 */
@Controller
@RequestMapping("view/admin")
public class LoginView {

    @RequestMapping("login")
    public String login() {
        return "login";
    }
}
