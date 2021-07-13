package com.deltaqin.sys.user.controller;

import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.constant.CodeConstant;
import com.deltaqin.sys.common.constant.MessageConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author deltaqin
 * @version 1.0
 * @date 2020/4/10 9:08 下午
 */

// 公共异常处理类
@RestControllerAdvice
public class BaseExceptionHandler {
    /**
     * 处理 value = Exception.class 类型的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult exception(Exception e) {
        e.printStackTrace();
        if(e.getMessage().contains("InternalAuthenticationServiceException")){
            return new CommonResult( CodeConstant.FAILED.getCode(), MessageConstant.ACCOUNT_DISABLED,null);
        }
        return new CommonResult( CodeConstant.FAILED.getCode(), e.getMessage(),null);
    }
}
