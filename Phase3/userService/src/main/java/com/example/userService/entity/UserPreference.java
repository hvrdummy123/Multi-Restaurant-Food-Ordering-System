package com.example.userService.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userpreferences")
public class    UserPreference
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preferred_cuisine", length = 50)
    private String preferredCuisine;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // Constructors
    public UserPreference() {}

    public UserPreference(Long id, String preferredCuisine, User user) {
        this.id = id;
        this.preferredCuisine = preferredCuisine;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferredCuisine() {
        return preferredCuisine;
    }

    public void setPreferredCuisine(String preferredCuisine) {
        this.preferredCuisine = preferredCuisine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
