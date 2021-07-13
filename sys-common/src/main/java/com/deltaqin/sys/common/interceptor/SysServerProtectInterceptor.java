package com.deltaqin.sys.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 微服务防护
 * @author deltaqin
 * @date 2020/9/13 3:53 下午
 */
@Slf4j
public class SysServerProtectInterceptor implements HandlerInterceptor {

    // 该拦截器可以拦截所有Web请求。在preHandle方法中，通过HttpServletRequest获取请求头
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头 GATEWAY_HEADER 中获取   GATEWAY_SECRET
        String gateway_secret = request.getHeader(AuthConstant.GATEWAY_HEADER);
        String gateway_secret_constant = new String(Base64Utils.encode(AuthConstant.GATEWAY_SECRET.getBytes()));

        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        map.forEach( (key,v)-> {
            log.info(key+v);
        });
        log.info(gateway_secret);
        log.info(gateway_secret_constant);
        // 校验 正确性
        if (StringUtils.equals(gateway_secret_constant, gateway_secret)) {
            return true;
        } else {
            CommonResult commonResult = new CommonResult().failed("请通过网关获取资源");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(commonResult));
            return false;
        }

    }
}
