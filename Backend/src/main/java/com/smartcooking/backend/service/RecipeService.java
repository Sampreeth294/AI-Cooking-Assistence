package com.smartcooking.backend.service;

import com.smartcooking.backend.model.Ingredient;
import com.smartcooking.backend.model.Recipe;
import com.smartcooking.backend.repository.IngredientRepository;
import com.smartcooking.backend.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    /**
     * =================================================
     * 1️⃣ RECIPE SUGGESTION (BASED ON INGREDIENT MATCH)
     * =================================================
     */
    public List<Recipe> suggestRecipes(String inputIngredients) {

        // Convert input string to list
        List<String> userIngredients = Arrays.stream(inputIngredients.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        // recipeId -> match count
        Map<Long, Integer> recipeScoreMap = new HashMap<>();

        // Compare user ingredients with DB ingredients
        for (Ingredient ingredient : ingredientRepository.findAll()) {

            String dbIngredient = ingredient.getName().toLowerCase();

            if (userIngredients.contains(dbIngredient)) {
                Long recipeId = ingredient.getRecipe().getId();
                recipeScoreMap.put(
                        recipeId,
                        recipeScoreMap.getOrDefault(recipeId, 0) + 1
                );
            }
        }

        // Sort recipes by match score
        return recipeScoreMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .map(entry -> recipeRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * =========================================
     * 2️⃣ GET FULL RECIPE DETAILS BY DISH NAME
     * =========================================
     */
    public Map<String, Object> getRecipeDetails(String recipeName) {

        Map<String, Object> response = new HashMap<>();

        Recipe recipe = recipeRepository
                .findByNameIgnoreCase(recipeName)
                .orElse(null);

        if (recipe == null) {
            response.put("error", "Recipe not found");
            return response;
        }

        // Fetch ingredients of recipe
        List<Ingredient> ingredients =
                ingredientRepository.findByRecipe(recipe);

        // Extract ingredient names only
        List<String> ingredientNames = ingredients.stream()
                .map(Ingredient::getName)
                .toList();

        response.put("name", recipe.getName());
        response.put("category", recipe.getCategory());
        response.put("cookTime", recipe.getCookTime());
        response.put("steps", recipe.getSteps());
        response.put("ingredients", ingredientNames);

        return response;
    }
}
