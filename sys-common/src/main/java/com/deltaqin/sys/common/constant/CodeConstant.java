package com.deltaqin.sys.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author deltaqin
 * @date 2020/9/8 12:53 上午
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CodeConstant {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private int code;
    private String message;

}
