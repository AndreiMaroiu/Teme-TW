package com.demo.model;

import com.demo.entity.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDto {

    private String personName;

    private String userName;

    private String role;

    public UserAccountDto(UserAccount userAccount) {
        this.personName = userAccount.getUserData().getFirstName() + " " + userAccount.getUserData().getLastName();
        this.userName = userAccount.getUsername();
        this.role = userAccount.getRole().getName();
    }
}
