package com.deltaqin.sys.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.properties.SysGatewayProperties;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * @author deltaqin
 * @date 2020/9/8 9:33 上午
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

//    private static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    private SysGatewayProperties properties;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("request = {}", JSONArray.toJSONString( exchange.getRequest()) );

        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);

        // 设置微服务防护(必须放这里，白名单可能在if就离开这里了，但是又必须设置白名单去JWT，否则401)
        byte[] encode = Base64Utils.encode((AuthConstant.GATEWAY_SECRET).getBytes());
        ServerHttpRequest request2 = exchange.getRequest().mutate().header(AuthConstant.GATEWAY_HEADER, new String(encode)).build();
        exchange = exchange.mutate().request(request2).build();

        if (StrUtil.isEmpty(token)) {

            return chain.filter(exchange);
        }
        try {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 禁止客户端的访问资源逻辑
            Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
            if (checkForbidUriResult != null) {
                return checkForbidUriResult;
            }

            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter.filter() user:{}",userStr);
            ServerHttpRequest request1 = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
            Map userInfo = (Map)JSON.parse(userStr);
            request1.mutate().header("cid", String.valueOf(userInfo.get("cid"))).build();
            request1.mutate().header("id", String.valueOf(userInfo.get("id"))).build();
            request1.mutate().header("user_name", String.valueOf(userInfo.get("user_name"))).build();
            exchange = exchange.mutate().request(request1).build();

            // 打印日志
            printLog(exchange);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

//    拦截被禁止的URI
    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            CommonResult sysResponse = new CommonResult().failed("该URI不允许外部访问");
            return makeResponse(response, sysResponse);
        }
        return null;
    }

    private Mono<Void> makeResponse(ServerHttpResponse response, CommonResult sysResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(sysResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

//    打印转发请求
    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }
}
