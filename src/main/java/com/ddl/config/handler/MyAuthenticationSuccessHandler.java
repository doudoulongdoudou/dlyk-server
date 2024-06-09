package com.ddl.config.handler;

import com.ddl.config.constant.Constants;
import com.ddl.result.R;
import com.ddl.entity.User;
import com.ddl.service.RedisService;
import com.ddl.util.JSONUtils;
import com.ddl.util.JWTUtils;
import com.ddl.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        String loginAct = user.getLoginAct();
        String sql = "UPDATE t_user SET last_login_time = ? WHERE login_act = ?";
        jdbcTemplate.update(sql,new Date(), loginAct);

        //1.生成JWT
        String userJSON = JSONUtils.toJSON(user);
        String jwt = JWTUtils.createJWT(userJSON);

        //2.写入redis
        redisService.setValue(Constants.REDIS_JWT_KEY + user.getId(), jwt);

        //3.设置jwt的过期时间，如果选择了 记住我  过期时间是7天，否则是30分钟
        String rememberMe = request.getParameter("rememberMe");
        if (Boolean.parseBoolean(rememberMe)){
            //写入redis 7天
            redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
        }else {
            //写入redis 30分钟
            redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        }

        //登录成功的统一结果
        R result = R.OK(jwt);

        //把R对象转成json
        String resultJSON = JSONUtils.toJSON(result);

        //把json写出去，写到浏览器
        ResponseUtils.write(response, resultJSON);
    }
}
