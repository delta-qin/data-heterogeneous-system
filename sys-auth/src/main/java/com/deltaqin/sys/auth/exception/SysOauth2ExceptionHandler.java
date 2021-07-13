package com.deltaqin.sys.auth.exception;

import com.deltaqin.sys.common.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常
 * @author deltaqin
 * @date 2020/9/8 8:28 上午
 */
@Slf4j
@ControllerAdvice
public class SysOauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {
        log.error(e.getMessage());
        return CommonResult.failed(e.getMessage());
    }
}
