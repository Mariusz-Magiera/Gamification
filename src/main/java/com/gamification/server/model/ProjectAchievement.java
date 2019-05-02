package com.gamification.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "project_achievement")
public class ProjectAchievement {

    @Id @GeneratedValue private Integer id;
    @ManyToOne @JoinColumn(name = "project") @JsonIgnore private Project project;
    @Column(name = "points") private Integer points;
    @Column(name = "description", columnDefinition = "TEXT") private String description;

    public ProjectAchievement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
