package com.ddl.web;

import com.ddl.entity.User;
import com.ddl.query.UserQuery;
import com.ddl.result.R;
import com.ddl.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: UserController
 * Package: com.ddl.web
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/4 15:45
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取登录用户的信息
     *
     * @param authentication
     * @return
     */
    @GetMapping("/api/login/info")
    public R getLoginInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return R.OK(user);
    }

    /**
     * 免登录功能
     *
     * @return
     */
    @GetMapping("/api/login/free")
    public R freeLogin() {
        log.info("触发免登录功能");
        return R.OK();
    }

    /**
     * 【分页查询】 查询所有用户信息
     *
     * @param current
     * @return
     */
    @GetMapping("/api/user/list")
    public R getUserList(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null) {
            current = 1;
        }
        PageInfo<User> userPageInfo = userService.getUserByPage(current);
        return R.OK(userPageInfo);
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/api/user/{id}")
    public R getUserDetail(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return R.OK(user);
    }

    /**
     * 新增用户
     * @param userQuery
     * @param token
     * @return
     */
    @PostMapping("/api/user")
    public R addUser(UserQuery userQuery, @RequestHeader("Authorization") String token){
        userQuery.setToken(token);
        //rows影响行数 >=1成功， 否则失败
        int rows = userService.saveUser(userQuery);
        return rows >= 1 ? R.OK() : R.FAIL();
    }

}
