package com.ddl.web;

import com.ddl.entity.User;
import com.ddl.result.R;
import com.ddl.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
