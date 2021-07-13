package com.deltaqin.sys.auth.controller;

import com.deltaqin.sys.auth.entities.SysToken;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author deltaqin
 * @date 2020/9/8 7:48 上午
 */
@RestController
@Api(value = "认证服务器，获取公钥以及token")
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @ApiOperation("获取token,封装token对象，返回token对象，不要直接请求，使用login来间接请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端公钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码")
    })
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<SysToken> postAccessToken(@ApiIgnore Principal principal,
                                                  @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        // tokenEndpoint.postAccessToken的流程很长，这一切都是为了生成Token，
        // 保存Token到TokenStore，之后返回Token
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        // 使用lombok的@builder来新建类
        SysToken sysToken = SysToken.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(sysToken);
    }
}
