package com.ddl.aspect;

import com.ddl.commons.DataScope;
import com.ddl.entity.User;
import com.ddl.query.BaseQuery;
import com.ddl.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * ClassName: DataScopeAspect
 * Package: com.ddl.aspect
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/10 20:46
 */

@Slf4j
@Aspect
@Component
public class DataScopeAspect {

    //切入点
    @Pointcut(value = "@annotation(com.ddl.commons.DataScope)")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //拿到方法上的注解
        DataScope dataScope = signature.getMethod().getDeclaredAnnotation(DataScope.class);
        String tableAlias = dataScope.tableAlias();
        String tableField = dataScope.tableField();

        //在spring web容器中 可以拿到当前请求的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        User user = JWTUtils.parseUserFromJWT(token);
        List<String> roleList = user.getRoleList();

        //判断用户角色是否是管理员
        if (!roleList.contains("admin")) {
            //普通用户，查询用户自己的数据, sql需要拼接
            Object params = joinPoint.getArgs()[0];
            if (params instanceof BaseQuery) {
                BaseQuery query = (BaseQuery) params;
                query.setFilterSQL(" and " + tableAlias + "." + tableField + " = " + user.getId());
            }
        }

        log.info("目标方法执行之前...");
        //执行目标方法，也就是    List<User> selectUserByPage(BaseQuery baseQuery);
        Object result = joinPoint.proceed();
        log.info("目标方法执行之后...");
        return result;
    }

}
