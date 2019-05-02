package com.gamification.server.model;

import javax.persistence.*;

@Entity
@Table(name = "project_to_user")
public class ProjectToUser {

    @Id @GeneratedValue private Integer id;
    @Column(name = "project") private Integer project;
    @Column(name = "user") private Integer user;

    public ProjectToUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
