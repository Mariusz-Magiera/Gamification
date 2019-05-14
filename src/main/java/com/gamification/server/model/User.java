package com.gamification.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
    @ManyToOne @JoinColumn(name = "permission") private Permission permission;
    @Column(name = "name") private String name;
    @Column(name = "password") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProfileLink> links;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAchievement> achievements;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "project_to_user",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "project")
    )
    private Set<Project> projects;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileLink> getLinks() {
        return links;
    }

    public void setLinks(Set<ProfileLink> links) {
        this.links = links;
    }

    public Set<UserAchievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<UserAchievement> achievements) {
        this.achievements = achievements;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
