package com.deltaqin.sys.common.constant;

/**
 * @author deltaqin
 * @date 2020/9/8 8:01 上午
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 管理client_id
     */
    String ADMIN_CLIENT_ID = "deltaqin";

    /**
     * 前台商城client_id
     */
    String USER_CLIENT_ID = "deltaqinuser";

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/sys-user/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     * 微服务防护
     */
    String GATEWAY_SECRET = "gateway_deltaqin";
    String GATEWAY_HEADER = "gateway";

}
