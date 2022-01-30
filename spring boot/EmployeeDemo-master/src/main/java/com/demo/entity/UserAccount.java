package com.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "T_USERACCOUNT")
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private boolean active;

    @OneToOne(mappedBy = "userAccount")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

}