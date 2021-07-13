package com.deltaqin.sys.auth.service.impl;

import com.deltaqin.sys.auth.entities.SysAuthUser;
import com.deltaqin.sys.auth.service.SysAdminService;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.constant.MessageConstant;
import com.deltaqin.sys.common.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息加载实现类，认证服务器需要使用
 * @author deltaqin
 * @date 2020/9/6 5:40 下午
 */
@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysAdminService adminService;
//    @Autowired
//    private SysMemberService memberService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        User user;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            user = adminService.loadUserByUsername(username);
        }else{
//            user = memberService.loadUserByUsername(username);
            user = null;
        }
        if (user==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        user.setClientId(clientId);
        SysAuthUser securityUser = new SysAuthUser(user);



        if (!securityUser.isEnabled()) {
//            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
