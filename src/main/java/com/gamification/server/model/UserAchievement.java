package com.gamification.server.model;

import javax.persistence.*;

@Entity
@Table(name = "user_achievement")
public class UserAchievement {

    @Id @GeneratedValue private Integer id;
    @Column(name = "user") private Integer user;
    @Column(name = "points") private Integer points;
    @Column(name = "description", columnDefinition = "TEXT") private String description;

    public UserAchievement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
