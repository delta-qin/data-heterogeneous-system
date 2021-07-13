package com.deltaqin.sys.remote_data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * jdbc数据源配置实体类(job_jdbc_datasource)
 *
 * @author zhouhongfa@gz-yibo.com
 * @version v1.0
 * @since 2019-07-30
 */

@Data
@ApiModel
public class DatasourceConfiguration {

    @ApiModelProperty(value = "数据源名称")
    private String dbDescription;

    @ApiModelProperty(value = "数据源类型")
    private int typeId;

    @ApiModelProperty(value = "用户名")
    private String dbUser;

    @ApiModelProperty(value = "密码")
    private String dbPassword;

    @ApiModelProperty(value = "jdbc url")
    private String dbUrl;

    @ApiModelProperty(value = "数据库名", hidden = true)
    private String databaseName;
}
