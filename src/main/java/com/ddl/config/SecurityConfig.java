package com.ddl.config;

import com.ddl.config.handler.MyAuthenticationFailureHandler;
import com.ddl.config.handler.MyAuthenticationSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ClassName: SecurityConfig
 * Package: com.ddl.config
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/3 16:12
 */

@Configuration
public class SecurityConfig {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 解决加密方式问题
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin( (formLogin) -> {
                    formLogin.loginProcessingUrl("/api/login")
                            .usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                .authorizeHttpRequests((authorize)->{
                    //任何请求都需要登录后才能访问
                    authorize.anyRequest().authenticated();
                })
                .build();
    }
}
