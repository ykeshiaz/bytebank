package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanDAO {
    public List <MealPlan> getAllMealPlansByUserId(Long userId, LocalDate startDate, LocalDate endDate);
    public MealPlan getMealPlanById(Long mealPlanId);
    public void saveMealPlan(LocalDate date, Long userId);
    public void updateMealPlan(Long mealPlanId, String meal, Long addRecipeId);

}
