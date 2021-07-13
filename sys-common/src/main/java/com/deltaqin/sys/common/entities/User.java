
package com.deltaqin.sys.common.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User {
    private Long id;
    private Long cid;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

}
