package com.achi.tw.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

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

    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<User> users;
}

