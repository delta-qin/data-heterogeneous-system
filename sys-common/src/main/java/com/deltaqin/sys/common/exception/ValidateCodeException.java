package com.deltaqin.sys.common.exception;

/**
 * @author deltaqin
 * @date 2020/9/6 3:31 下午
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
