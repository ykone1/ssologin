package com.security.service;

import com.security.domain.ResponseResult;
import com.security.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Auther:yukemeng Date:2022/4/26-04-26-16:48
 * Description:
 * version:1.0
 */
@Service
public interface LoginService {

    ResponseResult loginByUsernameCode(User user);
}
