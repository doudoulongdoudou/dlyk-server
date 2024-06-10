package com.ddl.commons;

import java.lang.annotation.*;

/**
 * ClassName: DataScope
 * Package: com.ddl.commons
 * Description:
 * 数据范围注解
 * @Author 豆豆龙
 * @Create 2024/6/10 20:32
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    //要在sql语句的末尾添加一个过滤条件

    /**
     * 表的别名
     * @return
     */
    public String tableAlias() default "";

    /**
     * 表的字段名
     * @return
     */
    public String tableField() default "";

}
