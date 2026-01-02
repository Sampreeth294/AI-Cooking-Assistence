package com.smartcooking.backend.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CsvDataGenerator {

    // Sample recipe names
    static String[] recipeNames = {
            "Tomato Curry", "Aloo Fry", "Veg Pulao", "Veg Fried Rice",
            "Paneer Butter Masala", "Palak Paneer", "Mushroom Curry",
            "Dal Tadka", "Chole Masala", "Vegetable Soup"
    };

    // Ingredient pool
    static String[] ingredientsPool = {
            "tomato", "onion", "potato", "paneer", "spinach",
            "mushroom", "rice", "oil", "butter", "cream",
            "garlic", "ginger", "spices", "chilli"
    };

    // MAIN METHOD
    public static void main(String[] args) {
        try {
            generateRecipesCSV();
            generateIngredientsCSV();
            System.out.println("✅ recipes.csv and ingredients.csv generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate recipes.csv
    private static void generateRecipesCSV() throws IOException {

        FileWriter writer = new FileWriter("recipes.csv");
        writer.append("name,category,cook_time,steps\n");

        Random random = new Random();

        for (int i = 1; i <= 1000; i++) {
            String name = recipeNames[random.nextInt(recipeNames.length)] + " " + i;
            String category = "Veg";
            int cookTime = 10 + random.nextInt(40);
            String steps = "Heat oil, add ingredients, cook well and serve";

            writer.append(name).append(",")
                  .append(category).append(",")
                  .append(String.valueOf(cookTime)).append(",")
                  .append("\"").append(steps).append("\"\n");
        }

        writer.close();
    }

    // Generate ingredients.csv
    private static void generateIngredientsCSV() throws IOException {

        FileWriter writer = new FileWriter("ingredients.csv");
        writer.append("name,recipe_id\n");

        Random random = new Random();

        for (int recipeId = 1; recipeId <= 1000; recipeId++) {
            for (int j = 0; j < 4; j++) {
                String ingredient = ingredientsPool[random.nextInt(ingredientsPool.length)];
                writer.append(ingredient)
                      .append(",")
                      .append(String.valueOf(recipeId))
                      .append("\n");
            }
        }

        writer.close();
    }
}
