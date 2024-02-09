package com.techelevator.model;

import java.util.List;

public interface IngredientDAO {

    public void saveIngredient(Ingredient ingredient);

    public List<Ingredient> getIngredientList(Long recipeId);

    public Ingredient searchIngredient(String name);

    public void saveIngredientToRecipe(Ingredient ingredient, Long recipeId);

    public void deleteIngredientFromRecipe(Long ingredientId, Long recipeId);

    public void editIngredientName(Long ingredientId, String name);

    public void editIngredientQuantity(Long ingredientId, Long recipeId, String quantity);
}
