package com.gamification.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "profile_link")
public class ProfileLink {

    @Id @GeneratedValue private Integer id;
    @ManyToOne @JoinColumn(name = "user") @JsonIgnore private User user;
    @Column(name = "type") private String type;
    @Column(name = "url") private String url;

    public ProfileLink() {
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
