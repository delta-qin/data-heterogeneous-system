package com.deltaqin.sys.config;

import cn.hutool.core.util.ArrayUtil;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.entities.AuthorizationManager;
import com.deltaqin.sys.filter.IgnoreUrlsRemoveJwtFilter;
import com.deltaqin.sys.handler.RestAuthenticationEntryPoint;
import com.deltaqin.sys.handler.SysAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置(认证去auth，鉴权直接在这里)
 * @author deltaqin
 * @date 2020/9/8 9:15 上午
 */
// Spring Security + SpringMVC：使用HttpServletRequest、HttpServletResponse，和HttpSession
//    使用SpringMVC的方式，主配置使用的是extends WebSecurityConfigurerAdapter     @EnableWebSecurity
//
// Spring Security + SpringCloud Gateway是基于webFlux：使用ServerWebExchange和WebSession
//    使用WebFlux的方式，主配置使用的是@EnableWebFluxSecurity。

@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;
    //登录成功时调用的自定义处理类
    private final SysAccessDeniedHandler restfulAccessDeniedHandler;
    //无权限访问被拒绝时的自定义处理器
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // Enables JWT Resource Server support.
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        //对白名单路径，直接移除JWT请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class)).permitAll() //白名单配置,无需进行权限过滤的请求路径
                .anyExchange().access(authorizationManager)//鉴权管理器配置,里面包含了比较多的配置
                .and().exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)//处理未授权
                .authenticationEntryPoint(restAuthenticationEntryPoint)//处理未认证
                .and().csrf().disable();//必须支持跨域
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //  ROLE_
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
