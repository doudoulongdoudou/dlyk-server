package com.ddl.config.filter;

import com.ddl.config.constant.Constants;
import com.ddl.entity.User;
import com.ddl.result.CodeEnum;
import com.ddl.result.R;
import com.ddl.service.RedisService;
import com.ddl.util.JSONUtils;
import com.ddl.util.JWTUtils;
import com.ddl.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: TokenVerifyFilter
 * Package: com.ddl.config.filter
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/6 17:52
 */

@Component
public class TokenVerifyFilter extends OncePerRequestFilter {
    @Resource
    private RedisService redisService;

    //springboot框架的额ioc容器中已经创建好了该线程池
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录请求，此时还没有生成jwt，那不需要对登录请求进行jwt验证
        if (request.getRequestURI().equals(Constants.LOGIN_URI)) {
            //验证jwt通过了 ，让Filter链继续执行，也就是继续执行下一个Filter
            filterChain.doFilter(request, response);

        } else {

            String token = request.getHeader("Authorization");
            if (!StringUtils.hasText(token)) {
                //token验证未通过统一结果   token为空
                R result = R.FAIL(CodeEnum.TOKEN_IS_EMPTY);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }


            //验证token有没有被篡改过
            if (!JWTUtils.verifyJWT(token)) {
                //token验证未通过统一结果    token被篡改
                R result = R.FAIL(CodeEnum.TOKEN_IS_ERROR);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            User user = JWTUtils.parseUserFromJWT(token);
            String redisToken = (String) redisService.getValue(Constants.REDIS_JWT_KEY + user.getId());
            if (!StringUtils.hasText(redisToken)) {
                //token验证未通过统一结果    token过期
                R result = R.FAIL(CodeEnum.TOKEN_IS_EXPIRED);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            if (!token.equals(redisToken)) {
                //token验证未通过统一结果    token与redis的token比对
                R result = R.FAIL(CodeEnum.TOKEN_IS_NONE_MATCH);
                //把R对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把R以json返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //jwt验证通过了，那么在spring security的上下文环境中要设置一下，设置当前这个人是登录过的，你后续不要再拦截他了
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getLoginPwd(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //刷新一下token（异步处理）
//            new Thread(()->{
//                String rememberMe = request.getHeader("rememberMe");
//                if (Boolean.parseBoolean(rememberMe)){
//                    //写入redis 7天
//                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
//                }else {
//                    //写入redis 30分钟
//                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
//                }
//            }).start();

            //异步处理（更好的方式：使用线程池去执行）
            threadPoolTaskExecutor.execute(() -> {
                String rememberMe = request.getHeader("rememberMe");
                if (Boolean.parseBoolean(rememberMe)) {
                    //写入redis 7天
                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
                } else {
                    //写入redis 30分钟
                    redisService.expire(Constants.REDIS_JWT_KEY + user.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                }
            });

            //验证jwt通过了 ，让Filter链继续执行，也就是继续执行下一个Filter
            filterChain.doFilter(request, response);
        }
    }
}
