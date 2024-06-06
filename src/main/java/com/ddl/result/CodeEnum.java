package com.ddl.result;

import lombok.*;

/**
 * ClassName: CodeEnum
 * Package: com.ddl.result
 * Description:
 *
 * @Author 豆豆龙
 * @Create 2024/6/3 15:25
 */

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public enum CodeEnum {

    OK(200, "成功"),

    FAIL(500, "失败"),

    TOKEN_IS_EMPTY(901,"请求Token参数为空"),

    TOKEN_IS_ERROR(902,"请求Token有误"),

    TOKEN_IS_EXPIRED(903,"请求Token已过期"),

    TOKEN_IS_NONE_MATCH(904,"请求Token不匹配"),

    USER_LOGOUT("登出成功");

    //结果码
    private int code;

    //结果信息
    @NonNull
    private String msg;

}
