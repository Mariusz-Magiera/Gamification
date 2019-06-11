package com.gamification.server.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer type;

    @Column(name="name") private String name;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
    private Set<User> users;

    public Permission() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
