package com.tw.app.utils;

import com.tw.app.entities.User;
import com.tw.app.services.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils
{
    public static User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((MyUserDetails)principal).getUser();
    }
}
