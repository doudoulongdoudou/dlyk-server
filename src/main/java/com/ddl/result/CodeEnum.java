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
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public enum CodeEnum {

    OK(200, "成功"),

    FAIL(500, "失败");

    //结果码
    private int code;

    //结果信息
    private String msg;
}
