package com.smartcooking.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    @Column(name = "cook_time")
    private int cookTime;

    // ✅ NEW: Preparation steps
    @Column(columnDefinition = "TEXT")
    private String steps;

    // ---------- Constructors ----------
    public Recipe() {
    }

    public Recipe(String name, String category, int cookTime, String steps) {
        this.name = name;
        this.category = category;
        this.cookTime = cookTime;
        this.steps = steps;
    }

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    // ✅ Getter & Setter for steps
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
