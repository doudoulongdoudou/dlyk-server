package com.ddl.config;

import com.ddl.config.constant.Constants;
import com.ddl.config.filter.TokenVerifyFilter;
import com.ddl.config.handler.MyAuthenticationFailureHandler;
import com.ddl.config.handler.MyAuthenticationSuccessHandler;
import com.ddl.config.handler.MyLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * ClassName: SecurityConfig
 * Package: com.ddl.config
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/3 16:12
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private TokenVerifyFilter tokenVerifyFilter;

    /**
     * 解决加密方式问题
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CorsConfigurationSource configurationSource) throws Exception {
        return httpSecurity
                //以下是表单登录相关配置
                .formLogin((formLogin) -> {
                    //登录表单提交的地址，可以自定义
                    formLogin.loginProcessingUrl(Constants.LOGIN_URI)
                            .usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                //以下是验证请求拦截和放行配置
                .authorizeHttpRequests((authorize) -> {
                    //只允许登录接口能访问 也是可以写在上面的formLogin后面加.permitAll()
                    authorize.requestMatchers("/api/login").permitAll()
                            //其他任何请求都需要登录后才能访问
                            .anyRequest().authenticated();
                })

                //方法引用，禁用跨站请求伪造
                .csrf(AbstractHttpConfigurer::disable)

                //支持跨域请求
                .cors((cors) -> {
                    cors.configurationSource(configurationSource);
                })

                //禁用session
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })

                //添加自定义Filter
                .addFilterBefore(tokenVerifyFilter, LogoutFilter.class)

                //退出登录配置
                .logout((logout)->{
                    logout.logoutUrl("/api/logout")
                            .logoutSuccessHandler(myLogoutSuccessHandler);
                })

                .build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
