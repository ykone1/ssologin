package com.security.service.impl;

import com.security.domain.LoginUserDetailImpl;
import com.security.domain.ResponseResult;
import com.security.domain.User;
import com.security.handler.MyPasswordAuthenticationToken;
import com.security.service.LoginService;
import com.security.utils.JwtUtil;
import com.security.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @Auther:yukemeng Date:2022/4/26-04-26-16:51
 * Description:
 * version:1.0
 */
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult loginByUsernameCode(User user) {

        MyPasswordAuthenticationToken myPasswordAuthenticationToken =
                new MyPasswordAuthenticationToken(user.getUserName(),user.getPassword());
        // 将认证信心交给manager管理
        Authentication authenticate = authenticationManager.authenticate(myPasswordAuthenticationToken);
        // 认证完成
        if(Objects.isNull(authenticate)){
            throw new UsernameNotFoundException("认证失败");
        }
        LoginUserDetailImpl loginUser = (LoginUserDetailImpl) authenticate.getDetails();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        redisCache.setCacheObject("login" + userId,loginUser);
        HashMap map = new HashMap();
        map.put("token", jwt);
        return new ResponseResult(200,"登录成功", map);
    }
}
