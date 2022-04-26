package com.security.controller;

import com.security.domain.ResponseResult;
import com.security.domain.User;
import com.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:yukemeng Date:2022/4/26-04-26-16:42
 * Description:
 * version:1.0
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/loginByUsername")
    public ResponseResult longinByUsernameCode(@RequestBody User user){
        ResponseResult result = loginService.loginByUsernameCode(user);
        return null;
    }
}
