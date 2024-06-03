package com.ddl.config.handler;

import com.ddl.result.R;
import com.ddl.entity.User;
import com.ddl.util.JSONUtils;
import com.ddl.util.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        //登录成功的统一结果
        R result = R.OK(user);

        //把R对象转成json
        String resultJSON = JSONUtils.toJSON(result);

        //把json写出去，写到浏览器
        ResponseUtils.write(response, resultJSON);
    }
}
