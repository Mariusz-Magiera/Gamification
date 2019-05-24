package com.gamification.server.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer type;

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
}
