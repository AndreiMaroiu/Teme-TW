package com.achi.tw.app.Services;

import java.util.ArrayList;
import java.util.List;

import com.achi.tw.app.Dao.DaoHelper;
import com.achi.tw.app.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        List<User> users = (List<User>)DaoHelper.doTransaction(service -> service.createQuery("from User", User.class).list());
        User user = null;

        for (var entity : users)
        {
            if (entity.getUsername().equals(userName))
            {
                user = entity;
                break;
            }
        }

        if (user != null)
        {
            List<String> roleList = new ArrayList<String>();
            for (Role role : user.getRoles())
            {
                roleList.add(role.getRoleName());
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .disabled(user.isDisabled())
                    .accountExpired(user.isAccountExpired())
                    .accountLocked(user.isAccountLocked())
                    .credentialsExpired(user.isCredentialsExpired())
                    .roles(getRoles(user))
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }

    String[] getRoles(User user)
    {
        List<Role> userRoles = user.getRoles();
        String[] roles = new String[userRoles.size()];
        return userRoles.toArray(roles);
    }
}
