package com.gamification.server.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id @GeneratedValue private Integer id;
    @Column(name = "name") private String name;
    @Column(name = "description", columnDefinition = "TEXT") private String description;

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
