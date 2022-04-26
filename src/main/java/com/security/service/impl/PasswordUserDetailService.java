package com.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther:yukemeng Date:2022/4/26-04-26-18:09
 * Description:
 * version:1.0
 */
@Component
public class PasswordUserDetailService implements UserDetailsService{



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 根据用户名查找出 权限
        return null;
    }
}
