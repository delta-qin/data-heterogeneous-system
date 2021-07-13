package com.deltaqin.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRemoteInfluxPanal implements Serializable {
    private Long id;

    @ApiModelProperty(value = "公司ID")
    private Long cid;

    @ApiModelProperty(value = "数据库ID，标记请求时候的数据源")
    private Long dbId;

    @ApiModelProperty(value = "SQL查询语句")
    private String sqlStatement;

    @ApiModelProperty(value = "面板类型")
    private Long panalTypeId;

    @ApiModelProperty(value = "面板名字")
    private String panalName;

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

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public Long getPanalTypeId() {
        return panalTypeId;
    }

    public void setPanalTypeId(Long panalTypeId) {
        this.panalTypeId = panalTypeId;
    }

    public String getPanalName() {
        return panalName;
    }

    public void setPanalName(String panalName) {
        this.panalName = panalName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", dbId=").append(dbId);
        sb.append(", sqlStatement=").append(sqlStatement);
        sb.append(", panalTypeId=").append(panalTypeId);
        sb.append(", panalName=").append(panalName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}