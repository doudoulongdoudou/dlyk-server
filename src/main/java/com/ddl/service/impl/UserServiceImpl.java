package com.ddl.service.impl;

import com.ddl.config.constant.Constants;
import com.ddl.entity.User;
import com.ddl.mapper.UserMapper;
import com.ddl.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (user == null) {
            throw new UsernameNotFoundException("登录账号不存在");
        }
        return user;
    }

    @Override
    public PageInfo<User> getUserByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<User> list = userMapper.selectUserByPage();
        PageInfo<User> info = new PageInfo<>(list);
        return info;
    }
}
