package com.deltaqin.sys.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author deltaqin
 * @date 2020/9/29 3:39 下午
 */
@Data
public class SysCompanyParam {
    private Long id;

    @ApiModelProperty(value = "公司用户名")
    private String name;

    @ApiModelProperty(value = "公司创建、用户注册时候的旧密码")
    private String oldPassword;

    @ApiModelProperty(value = "公司创建、用户注册时候的新密码")
    private String newPassword;


}
