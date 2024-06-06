package com.ddl.config.handler;

import com.ddl.config.constant.Constants;
import com.ddl.entity.User;
import com.ddl.result.CodeEnum;
import com.ddl.result.R;
import com.ddl.service.RedisService;
import com.ddl.util.JSONUtils;
import com.ddl.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName: MyLogoutSuccessHandler
 * Package: com.ddl.config.handler
 * Description:
 * 退出登录处理器
 * @Author 豆豆龙
 * @Create 2024/6/6 22:46
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisService redisService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //退出成功，执行该方法，在该方法中返回json给前端，就行了
        User tUser = (User) authentication.getPrincipal();

        //删除一下redis中用户的jwt
        redisService.removeValue(Constants.REDIS_JWT_KEY + tUser.getId());

        //退出成功的统一结果
        R result = R.OK(CodeEnum.USER_LOGOUT);

        //把R对象转成json
        String resultJSON = JSONUtils.toJSON(result);

        //把R以json返回给前端
        ResponseUtils.write(response, resultJSON);
    }
}
