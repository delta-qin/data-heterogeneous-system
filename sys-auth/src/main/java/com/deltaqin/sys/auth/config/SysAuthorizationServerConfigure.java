package com.deltaqin.sys.auth.config;

import com.deltaqin.sys.auth.enhancer.JwtTokenEnhancer;
import com.deltaqin.sys.auth.service.impl.SysUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class SysAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    // client_secret字段加解密器。
    private final PasswordEncoder passwordEncoder;
    // 用户信息加载实现类
    private final SysUserDetailsServiceImpl sysUserDetailsService;
    // 密码模式下需设置一个AuthenticationManager对象,获取 UserDetails信息
    private final AuthenticationManager authenticationManager;
    // token信息的额外信息处理。
    private final JwtTokenEnhancer jwtTokenEnhancer;

    // clientDetailsService注入，决定clientDeatils信息的处理服务。
    // ClientDetailsServiceConfigurer（从您的回调AuthorizationServerConfigurer）可以用来在内存或JDBC实现客户的细节服务。
    // 客户端的重要属性是
    //clientId：（必填）客户端ID。
    //secret:(可信客户端需要）客户机密码（如果有）。没有可不填
    //scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
    //authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。
    //authorities授予客户的授权机构（普通的Spring Security权威机构）。
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("deltaqin")
                .secret(passwordEncoder.encode("deltaqin"))
                .scopes("all")
                // .authorities("ROLE_CLIENT")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600*24)
                .refreshTokenValiditySeconds(3600*24*7);
    }

    /**
     * 访问端点配置。tokenStroe、tokenEnhancer服务
     * AuthorizationServerEndpointsConfigurer其实是一个装载类，装载Endpoints所有相关的类配置
     * （AuthorizationServer、TokenServices、TokenStore、ClientDetailsService、UserDetailsService）。
     * 注入相关配置：
     * 1. 密码模式下配置认证管理器 AuthenticationManager
     * 2. 设置 AccessToken的存储介质tokenStore， 默认使用内存当做存储介质。
     * 3. userDetailsService注入
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer); // 配置JWT的内容增强器  附加了用户的ID以及clientID
        delegates.add(accessTokenConverter()); // 使用了非对称加密，非对称加密（RSA）私钥生成和公钥解析JWT令牌。
        enhancerChain.setTokenEnhancers(delegates); //

        endpoints.authenticationManager(authenticationManager) // //MUST：密码模式下需设置一个AuthenticationManager对象,获取 UserDetails信息
                .userDetailsService(sysUserDetailsService) //配置加载用户信息的服务
                .accessTokenConverter(accessTokenConverter()) //. 告诉spring security token的生成方式
                .tokenEnhancer(enhancerChain); //token里加点信息
    }

    //   访问安全配置
    //   AuthorizationServerSecurityConfigurer继承SecurityConfigurerAdapter.也就是一个 Spring Security安全配置
    //   提供给AuthorizationServer去配置AuthorizationServer的端点（/oauth/****）的安全访问规则、过滤器Filter。
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证。针对/oauth/token端点。
        security.allowFormAuthenticationForClients();
    }



    //    非对称加密（RSA）私钥生成和公钥解析JWT令牌。
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        // JwtAccessTokenConverter 定义token的生成方式，这里使用JWT生成token，
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        // 服务端生成公钥和密钥，每个客户端使用获取到的公钥到服务器做认证。
        // 因此首先要生成一个证书，导出公钥再后续步骤
        ////从classpath下获取RSA秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "deltaqin".toCharArray());
        // 包含了公钥和私钥
        return keyStoreKeyFactory.getKeyPair("jwt", "deltaqin".toCharArray());
    }
}
