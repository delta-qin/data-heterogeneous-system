package com.deltaqin.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRemoteInfluxInfo implements Serializable {
    private Long id;

    @ApiModelProperty(value = "公司ID")
    private Long cid;

    @ApiModelProperty(value = "influx用户名")
    private String username;

    @ApiModelProperty(value = "influx密码")
    private String password;

    @ApiModelProperty(value = "influx的URL+端口")
    private String url;

    @ApiModelProperty(value = "influx数据库名字")
    private String dbName;

    @ApiModelProperty(value = "数据源的昵称(方便鉴别）")
    private String sourceName;

    private String address;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", url=").append(url);
        sb.append(", dbName=").append(dbName);
        sb.append(", sourceName=").append(sourceName);
        sb.append(", address=").append(address);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}