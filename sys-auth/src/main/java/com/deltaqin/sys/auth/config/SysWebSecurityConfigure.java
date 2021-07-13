package com.deltaqin.sys.auth.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author deltaqin
 * @date 2020/9/6 5:10 下午
 */

// 开启了和Web相关的安全配置
//授权端点的 URL 应该被 Spring Security 保护起来只供授权用户访问
// SecurityConfigure用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，
// 刷新令牌的请求地址都是以/oauth/开头的，也就是说 SecurityConfigure用于处理和令牌相关的请求；
@EnableWebSecurity
//@Order(2)
public class SysWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //http.authorizeRequests()方法有多个子节点，每个macher按照他们的声明顺序执行
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()// 拦截所有endpoint，任何人都可以获取
                .antMatchers("/oauth/rsa/publicKey").permitAll() // 公钥任何人都可以获取
                .antMatchers("/v2/api-docs").permitAll() // api文档也是任何人都可以获取
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated(); // 其余任何请求都必须经过身份验证，否则Spring应用程序将返回401响应
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
