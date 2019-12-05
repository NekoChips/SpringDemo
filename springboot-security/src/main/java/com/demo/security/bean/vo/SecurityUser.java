package com.demo.security.bean.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.demo.security.constants.StatusConstants;

import lombok.Data;

/**
 * ClassName: SecurityUser <br/>
 * Description: SecurityUser 相关<br/>
 * date: 2019/12/5 12:38<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class SecurityUser implements UserDetails
{
    private static final long serialVersionUID = 3689777856818259621L;

    private String userId;

    private String username;

    private String password;

    private List<String> roles;

    private List<String> perms;

    private Integer status;

    public SecurityUser(String username, String password, List<String> perms)
    {
        this.username = username;
        this.password = password;
        this.perms = perms;
    }

    public SecurityUser(String username, String password, List<String> perms, Integer status)
    {
        this.username = username;
        this.password = password;
        this.perms = perms;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(perms))
        {
            perms.forEach(perm -> list.add(new SimpleGrantedAuthority(perm)));
        }
        return list;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return StatusConstants.STATUS_NORMAL.equals(this.status);
    }
}
