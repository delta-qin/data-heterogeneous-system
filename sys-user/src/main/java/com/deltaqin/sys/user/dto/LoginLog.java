package com.deltaqin.sys.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author deltaqin
 * @date 2020/9/24 8:12 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date loginTime;
    private Integer count;

}
