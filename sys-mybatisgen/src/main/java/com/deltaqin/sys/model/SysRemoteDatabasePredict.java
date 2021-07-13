package com.deltaqin.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRemoteDatabasePredict implements Serializable {
    private Long id;

    private Long cid;

    private Long panalId;

    @ApiModelProperty(value = "单位（/h）")
    private String method;

    private String phone;

    private Double threshold;

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

    public Long getPanalId() {
        return panalId;
    }

    public void setPanalId(Long panalId) {
        this.panalId = panalId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cid=").append(cid);
        sb.append(", panalId=").append(panalId);
        sb.append(", method=").append(method);
        sb.append(", phone=").append(phone);
        sb.append(", threshold=").append(threshold);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}