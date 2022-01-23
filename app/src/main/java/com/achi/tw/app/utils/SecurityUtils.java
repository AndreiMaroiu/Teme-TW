package com.achi.tw.app.utils;

import com.achi.tw.app.entity.User;
import com.achi.tw.app.services.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils
{
    public static User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((MyUserDetails) principal).getUser();
    }
}
