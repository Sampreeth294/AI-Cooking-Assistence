package com.smartcooking.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcooking.backend.model.Recipe;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // 🔍 Find recipe by dish name (case-insensitive)
    Optional<Recipe> findByNameIgnoreCase(String name);
}
