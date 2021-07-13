package com.deltaqin.sys.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author deltaqin
 * @date 2020/9/8 3:08 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty
    private String password;
    @ApiModelProperty(value = "公司ID", required = true)
    private Long cid;
}
