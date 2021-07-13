package com.deltaqin.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRemoteDatabasePanal implements Serializable {
    private Long id;

    @ApiModelProperty(value = "公司id")
    private Long cid;

    @ApiModelProperty(value = "是或否在主页显示(1为在）")
    private Integer state;

    @ApiModelProperty(value = "数据源id，用于获取数据源对应的所有面板")
    private Long dbId;

    @ApiModelProperty(value = "面板类型")
    private Long panalTypeId;

    @ApiModelProperty(value = "执行的sql")
    private String sqlWords;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "x轴")
    private String x;

    private Long departmentId;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public Long getPanalTypeId() {
        return panalTypeId;
    }

    public void setPanalTypeId(Long panalTypeId) {
        this.panalTypeId = panalTypeId;
    }

    public String getSqlWords() {
        return sqlWords;
    }

    public void setSqlWords(String sqlWords) {
        this.sqlWords = sqlWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", state=").append(state);
        sb.append(", dbId=").append(dbId);
        sb.append(", panalTypeId=").append(panalTypeId);
        sb.append(", sqlWords=").append(sqlWords);
        sb.append(", description=").append(description);
        sb.append(", x=").append(x);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}