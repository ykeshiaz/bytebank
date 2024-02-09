package com.techelevator.model;

import java.time.LocalDate;

public class MealPlan {

    private Long userId;
    private Long mealPlanId;
    private LocalDate date;
    private Long breakfastRecipeId;
    private Long lunchRecipeId;
    private Long dinnerRecipeId;
    private Long snackRecipeId;
    private Long dessertRecipeId;

    private Recipe breakfastRecipe;
    private Recipe lunchRecipe;
    private Recipe dinnerRecipe;
    private Recipe snackRecipe;
    private Recipe dessertRecipe;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getMealPlanId()
    {
        return mealPlanId;
    }

    public void setMealPlanId(Long mealPlanId)
    {
        this.mealPlanId = mealPlanId;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public Long getBreakfastRecipeId()
    {
        return breakfastRecipeId;
    }

    public void setBreakfastRecipeId(Long breakfastRecipeId)
    {
        this.breakfastRecipeId = breakfastRecipeId;
    }

    public Long getLunchRecipeId()
    {
        return lunchRecipeId;
    }

    public void setLunchRecipeId(Long lunchRecipeId)
    {
        this.lunchRecipeId = lunchRecipeId;
    }

    public Long getDinnerRecipeId()
    {
        return dinnerRecipeId;
    }

    public void setDinnerRecipeId(Long dinnerRecipeId)
    {
        this.dinnerRecipeId = dinnerRecipeId;
    }

    public Recipe getBreakfastRecipe()
    {
        return breakfastRecipe;
    }

    public void setBreakfastRecipe(Recipe breakfastRecipe)
    {
        this.breakfastRecipe = breakfastRecipe;
    }

    public Recipe getLunchRecipe()
    {
        return lunchRecipe;
    }

    public void setLunchRecipe(Recipe lunchRecipe)
    {
        this.lunchRecipe = lunchRecipe;
    }

    public Recipe getDinnerRecipe()
    {
        return dinnerRecipe;
    }

    public void setDinnerRecipe(Recipe dinnerRecipe)
    {
        this.dinnerRecipe = dinnerRecipe;
    }

    public Long getSnackRecipeId() {
        return snackRecipeId;
    }

    public void setSnackRecipeId(Long snackRecipeId) {
        this.snackRecipeId = snackRecipeId;
    }

    public Long getDessertRecipeId() {
        return dessertRecipeId;
    }

    public void setDessertRecipeId(Long dessertRecipeId) {
        this.dessertRecipeId = dessertRecipeId;
    }

    public Recipe getSnackRecipe() {
        return snackRecipe;
    }

    public void setSnackRecipe(Recipe snackRecipe) {
        this.snackRecipe = snackRecipe;
    }

    public Recipe getDessertRecipe() {
        return dessertRecipe;
    }

    public void setDessertRecipe(Recipe dessertRecipe) {
        this.dessertRecipe = dessertRecipe;
    }
}
