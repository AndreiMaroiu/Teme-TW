package com.achi.tw.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUser
{
    private String username;
    private String password;
    private String confirmedPassword;
    private String role;
}
