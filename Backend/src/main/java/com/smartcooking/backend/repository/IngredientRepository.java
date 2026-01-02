package com.smartcooking.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcooking.backend.model.Ingredient;
import com.smartcooking.backend.model.Recipe;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // 🔍 Get all ingredients for a given recipe
    List<Ingredient> findByRecipe(Recipe recipe);
}
