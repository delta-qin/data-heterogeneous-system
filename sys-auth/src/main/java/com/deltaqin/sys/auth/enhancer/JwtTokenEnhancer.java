package com.deltaqin.sys.auth.enhancer;

import com.deltaqin.sys.auth.entities.SysAuthUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 内容增强，token加入用户信息
 * @author deltaqin
 * @date 2020/9/7 11:15 下午
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SysAuthUser securityUser = (SysAuthUser) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户ID设置到JWT中
        info.put("id", securityUser.getId());
        info.put("client_id",securityUser.getClientId());
        info.put("cid",securityUser.getCid());
//        info.put("authorities",securityUser.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
