package com.demo.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.security.bean.entity.User;
import com.demo.security.bean.vo.SecurityUser;
import com.demo.security.service.UserService;

/**
 * ClassName: UserDetailsServiceImpl <br/>
 * Description: UserDetailsServiceImpl 相关<br/>
 * date: 2019/12/5 12:51<br/>
 *
 * @author Chenyangjie<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userService.queryUserByName(username);
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        List<String> permissions;
        if (user != null)
        {
            permissions = user.getPermissions();
            if (!CollectionUtils.isEmpty(permissions))
            {
                permissions.forEach(perm -> collection.add(new SimpleGrantedAuthority(perm)));
            }
            System.out.println(permissions);
        }
        else
        {
            throw new UsernameNotFoundException("user is not exist! username:[" + username + "]");
        }
        return new SecurityUser(user.getUsername(), user.getPassword(), permissions, user.getUserStatus());
    }
}
