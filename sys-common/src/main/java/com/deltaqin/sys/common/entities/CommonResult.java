package com.deltaqin.sys.common.entities;


import com.deltaqin.sys.common.constant.CodeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author deltaqin
 * @date 2020/9/6 3:28 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String  message;
    private T       data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(CodeConstant.SUCCESS.getCode(), CodeConstant.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(CodeConstant.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(CodeConstant.FAILED.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(CodeConstant.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(CodeConstant.UNAUTHORIZED.getCode(), CodeConstant.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(CodeConstant.FORBIDDEN.getCode(), CodeConstant.FORBIDDEN.getMessage(), data);
    }

    public static CommonResult failed() {
        return failed(CodeConstant.FAILED.getMessage());

    }
}
