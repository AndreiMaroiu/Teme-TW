package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean disabled;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    List<Role> roles;

}
