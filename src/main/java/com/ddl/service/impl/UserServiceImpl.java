package com.ddl.service.impl;

import com.ddl.config.constant.Constants;
import com.ddl.entity.Role;
import com.ddl.entity.User;
import com.ddl.manager.RedisManager;
import com.ddl.mapper.RoleMapper;
import com.ddl.mapper.UserMapper;
import com.ddl.query.BaseQuery;
import com.ddl.query.UserQuery;
import com.ddl.service.UserService;
import com.ddl.util.CacheUtils;
import com.ddl.util.JWTUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisManager redisManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByLoginAct(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录账号不存在");
        }

        //查询当前用户的角色
        List<Role> roleList = roleMapper.selectUserRoleByUserId(user.getId());
        //字符串的角色列表
        List<String> stringRoleList = new ArrayList<>();
        roleList.forEach(role -> {
            stringRoleList.add(role.getRole());
        });
        user.setRoleList(stringRoleList);
        return user;
    }

    @Override
    public PageInfo<User> getUserByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<User> list = userMapper.selectUserByPage(BaseQuery.builder().build());
        PageInfo<User> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectDetailById(id);
        return user;
    }

    @Transactional
    @Override
    public int saveUser(UserQuery userQuery) {
        User user = new User();
        BeanUtils.copyProperties(userQuery, user);

        //密码加密
        String encodePwd = passwordEncoder.encode(user.getLoginPwd());
        user.setLoginPwd(encodePwd);
        //创建时间
        user.setCreateTime(new Date());
        user.setEditTime(new Date());

        //获取token，从中取出登录用户的id
        String token = userQuery.getToken();
        Integer userId = JWTUtils.parseUserFromJWT(token).getId();
        user.setCreateBy(userId);
        user.setEditBy(userId);

        return userMapper.insert(user);
    }

    @Transactional
    @Override
    public int updateUser(UserQuery userQuery) {
        User user = new User();
        BeanUtils.copyProperties(userQuery, user);
        if (StringUtils.hasText(userQuery.getLoginPwd())) {
            //密码加密
            String encodePwd = passwordEncoder.encode(user.getLoginPwd());
            user.setLoginPwd(encodePwd);
        }
        //修改时间
        user.setEditTime(new Date());
        //获取token，从中取出登录用户的id
        String token = userQuery.getToken();
        Integer userId = JWTUtils.parseUserFromJWT(token).getId();
        user.setEditBy(userId);

        return userMapper.updateUserById(user);
    }

    @Transactional
    @Override
    public int delUserById(Integer id) {
        return userMapper.delUserById(id);
    }

    @Transactional
    @Override
    public int batchDelUserByIds(List<String> idList) {
        return userMapper.batchDelUserByIds(idList);
    }

    @Override
    public List<User> getOwner() {
        //数据在redis做缓存，如果redis查不到就从数据库查，在把数据库查到的数据写进redis

        return CacheUtils.getCacheData(
                () -> {
                    //生产，从reids查数据
                    return (List<User>) redisManager.getValue(Constants.REDIS_OWNER_KEY);
                },
                () -> {
                    //生产，从mysql查数据
                    return (List<User>) userMapper.selectByOwner();
                },
                (t) -> {
                    //消费，把数据写入redis
                    redisManager.setValue(Constants.REDIS_OWNER_KEY, t);
                }
        );
    }

}
