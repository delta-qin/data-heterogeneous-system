package com.deltaqin.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRemoteDatabaseInfo implements Serializable {
    private Long id;

    @ApiModelProperty(value = "公司id")
    private Long cid;

    @ApiModelProperty(value = "数据源url")
    private String dbUrl;

    @ApiModelProperty(value = "数据源用户名")
    private String dbUser;

    @ApiModelProperty(value = "数据源密码")
    private String dbPassword;

    @ApiModelProperty(value = "描述信息")
    private String dbDescription;

    private Long typeId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDescription() {
        return dbDescription;
    }

    public void setDbDescription(String dbDescription) {
        this.dbDescription = dbDescription;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", dbUrl=").append(dbUrl);
        sb.append(", dbUser=").append(dbUser);
        sb.append(", dbPassword=").append(dbPassword);
        sb.append(", dbDescription=").append(dbDescription);
        sb.append(", typeId=").append(typeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}