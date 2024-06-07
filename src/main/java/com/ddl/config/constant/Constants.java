package com.ddl.config.constant;

/**
 * ClassName: Constant
 * Package: com.ddl.config.constant
 * Description:
 * 常量类
 * @Author 豆豆龙
 * @Create 2024/6/5 11:33
 */
public class Constants {

    public static final String SESSION_CAPTCHA = "captcha";

    public static final String LOGIN_URI = "/api/login";

    //redis的key的命名规范--> 项目名:模块名:功能名:唯一业务参数(比如用户id)
    public static final String REDIS_JWT_KEY = "dlyk:user:login";

    //jwt过期时间7天
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;

    //jwt过期时间30分钟
    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60L;

    //分页每页显示10条数据
    public static final int PAGE_SIZE = 10;
}
