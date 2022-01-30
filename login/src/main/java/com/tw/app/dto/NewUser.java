package com.tw.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUser
{
    private String username;
    private String password;
    private String confirmedPassword;
    private String role;
}
