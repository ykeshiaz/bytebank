package com.techelevator.model;

import java.util.List;

public interface RecipeDAO {

    public void saveRecipe(Recipe recipe);

    public List<Recipe> searchRecipe(String recipeName);

    public void addRecipeSteps(String steps, Long recipeId);

    public Recipe getRecipeById(Long recipeId);

    public List<Recipe> getAllRecipesByUserId(Long userId);

    public void updateRecipeName(String newName, Long recipeId);

    public void updateRecipeType(String type, Long recipeId);

    //also delete ingredient and meal plan by recipeId
    public void deleteRecipe(Long recipeId);

    public void updateRecipeImagePath(Long recipeId, String imagePath);


    }
