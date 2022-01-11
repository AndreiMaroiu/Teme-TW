package com.achi.tw.app.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Transactional
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<User> users;
}

