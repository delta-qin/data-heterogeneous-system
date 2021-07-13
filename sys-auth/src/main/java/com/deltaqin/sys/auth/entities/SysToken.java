package com.deltaqin.sys.auth.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * token信息的封装
 * @author deltaqin
 * @date 2020/9/8 7:59 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SysToken {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
