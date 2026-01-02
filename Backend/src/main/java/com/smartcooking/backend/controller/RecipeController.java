package com.smartcooking.backend.controller;

import com.smartcooking.backend.model.Recipe;
import com.smartcooking.backend.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * =========================================
     * 1️⃣ SUGGEST RECIPES BASED ON INGREDIENTS
     * =========================================
     * Example:
     * /api/recipes?ingredients=tomato,onion
     */
    @GetMapping("/recipes")
    public List<Recipe> getRecipes(
            @RequestParam String ingredients) {

        return recipeService.suggestRecipes(ingredients);
    }

    /**
     * =========================================
     * 2️⃣ GET FULL RECIPE DETAILS BY DISH NAME
     * =========================================
     * Example:
     * /api/recipe?name=Tomato Curry
     */
    @GetMapping("/recipe")
    public Map<String, Object> getRecipeDetails(
            @RequestParam String name) {

        return recipeService.getRecipeDetails(name);
    }
}
