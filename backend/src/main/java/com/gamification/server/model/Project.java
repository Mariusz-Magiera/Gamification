package com.gamification.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id @GeneratedValue private Integer id;
    @Column(name = "name") private String name;
    @Column(name = "description", columnDefinition = "TEXT") private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectAchievement> achievements;

    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private Set<User> users;

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<ProjectAchievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<ProjectAchievement> achievements) {
        this.achievements = achievements;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
