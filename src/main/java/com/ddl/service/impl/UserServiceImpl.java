package com.ddl.service.impl;

import com.ddl.entity.User;
import com.ddl.mapper.UserMapper;
import com.ddl.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.ddl.service.impl
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/3 15:53
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByLoginAct(username);
        if (user ==null){
            throw new UsernameNotFoundException("登录账号不存在");
        }
        return user;
    }
}
