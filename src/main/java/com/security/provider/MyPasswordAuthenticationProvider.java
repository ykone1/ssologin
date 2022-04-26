package com.security.provider;

import com.security.handler.MyPasswordAuthenticationToken;
import com.security.service.impl.PasswordUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:yukemeng Date:2022/4/26-04-26-17:16
 * Description:
 * version:1.0
 */
public class MyPasswordAuthenticationProvider implements AuthenticationProvider {

    private PasswordUserDetailService passwordUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 真正的业务实现在这里面。
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 先获取用户名和密码
        String username = "";
        String password = "";
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        if(principal instanceof String && credentials instanceof String){
            username = (String) principal;
            password = (String) credentials;
        }else {
            throw new UsernameNotFoundException("用户名或密码格式存在问题，不是String类型");
        }

        // 数据中查找用户名和密码是否正确 TODO

        //  封装权限信息
        UserDetails userDetails = passwordUserDetailService.loadUserByUsername(username);
        List<? extends GrantedAuthority> list = (List<? extends GrantedAuthority>) userDetails.getAuthorities();

        MyPasswordAuthenticationToken myPasswordAuthenticationToken =
                new MyPasswordAuthenticationToken(principal, credentials, list);
        // 查出的所有的信息封装在 Details中
        myPasswordAuthenticationToken.setDetails(userDetails);

        return myPasswordAuthenticationToken;
    }

    /**
     * 支持的AuthenticationToken
     * @param authentication-
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == MyPasswordAuthenticationToken.class;
    }
}
