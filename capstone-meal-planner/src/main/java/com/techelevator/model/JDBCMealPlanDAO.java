package com.techelevator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCMealPlanDAO implements MealPlanDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    public JDBCMealPlanDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MealPlan> getAllMealPlansByUserId(Long userId, LocalDate startDate, LocalDate endDate) {

        String select = "SELECT mealplan_id, " +
                "       user_id, " +
                "       date, " +
                "       breakfast_recipe_id, " +
                "       lunch_recipe_id, " +
                "       dinner_recipe_id, " +
                "       snack_recipe_id, " +
                "       dessert_recipe_id " +
                "FROM meal_plan " +
                "WHERE user_id = ? " +
                "    AND date BETWEEN ? AND ?\n" +
                "ORDER BY date;";

        SqlRowSet checkRows = jdbcTemplate.queryForRowSet(select, userId, startDate, endDate);
        // check if all dates have a meal plan
        // if there are not 7 meal plans (one for each day) then you need to insert an empty meal plan
        // INSERT userid, date only
        if(!checkRows.next()){
            LocalDate currentDate = startDate;
            for (int i = 0; i < 7; i++) {
                saveMealPlan(currentDate, userId);
                currentDate = currentDate.plusDays(1);
            }
        }

        // get all mealPlans
        List<MealPlan> mealPlans = new ArrayList<>();
        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, userId, startDate, endDate);

        while (rows.next())
        {
            MealPlan mealPlan = mapRowToMealPlan(rows);
            // get recipes for all meal plans
            buildRecipes(mealPlan);
            mealPlans.add(mealPlan);
        }

        return mealPlans;
    }

    private void buildRecipes(MealPlan mealPlan)
    {
        if(mealPlan.getBreakfastRecipeId() != null)
        {
            Recipe recipe = recipeDAO.getRecipeById(mealPlan.getBreakfastRecipeId());
            mealPlan.setBreakfastRecipe(recipe);
        }

        if(mealPlan.getLunchRecipeId() != null)
        {
            Recipe recipe = recipeDAO.getRecipeById(mealPlan.getLunchRecipeId());
            mealPlan.setLunchRecipe(recipe);
        }

        if(mealPlan.getDinnerRecipeId() != null)
        {
            Recipe recipe = recipeDAO.getRecipeById(mealPlan.getDinnerRecipeId());
            mealPlan.setDinnerRecipe(recipe);
        }

        if(mealPlan.getSnackRecipeId() != null)
        {
            Recipe recipe = recipeDAO.getRecipeById(mealPlan.getSnackRecipeId());
            mealPlan.setSnackRecipe(recipe);
        }

        if(mealPlan.getDessertRecipeId() != null)
        {
            Recipe recipe = recipeDAO.getRecipeById(mealPlan.getDessertRecipeId());
            mealPlan.setDessertRecipe(recipe);
        }
    }

    @Override
    public MealPlan getMealPlanById(Long mealPlanId) {

        String select = "SELECT mealplan_id, " +
                "       user_id, " +
                "       date, " +
                "       breakfast_recipe_id, " +
                "       lunch_recipe_id, " +
                "       dinner_recipe_id, " +
                "       snack_recipe_id, " +
                "       dessert_recipe_id " +
                "FROM meal_plan " +
                "WHERE mealplan_id = ? ";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, mealPlanId);

        if(rows.next()){
            MealPlan mealPlan = mapRowToMealPlan(rows);
            return mealPlan;
        }
        return null;
    }

    @Override
    public void saveMealPlan(LocalDate date, Long userId) {
        String insert = "INSERT INTO meal_plan (mealplan_id, user_id, date) VALUES (?, ?, ?);";
        jdbcTemplate.update(insert, getNextMealPlanId(), userId, date);
    }

    private Long getNextMealPlanId() {
        SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT nextval('meal_plan_mealplan_id_seq') AS id;");
        if(row.next()) {
            return row.getLong("id");
        }
        return 0L;
    }

    @Override
    public void updateMealPlan(Long mealPlanId, String meal, Long addRecipeId) {
        String update = "UPDATE meal_plan SET ";

        switch (meal) {
            case "Breakfast":
                update += " breakfast_recipe_id = ? WHERE mealplan_id = ?; ";
                break;
            case "Lunch":
                update += " lunch_recipe_id = ? WHERE mealplan_id = ?; ";
                break;
            case "Dinner":
                update += " dinner_recipe_id = ? WHERE mealplan_id = ?; ";
                break;
            case "Snack":
                update += " snack_recipe_id = ? WHERE mealplan_id = ?; ";
                break;
            case "Dessert":
                update += " dessert_recipe_id = ? WHERE mealplan_id = ?; ";
                break;
        }

        jdbcTemplate.update(update, addRecipeId, mealPlanId);

    }



    private MealPlan mapRowToMealPlan(SqlRowSet row)
    {
        MealPlan mealPlan = new MealPlan();

        mealPlan.setMealPlanId(row.getLong("mealplan_id"));
        mealPlan.setUserId(row.getLong("user_id"));
        mealPlan.setDate(row.getDate("date").toLocalDate());
        mealPlan.setBreakfastRecipeId(row.getLong("breakfast_recipe_id"));
        mealPlan.setLunchRecipeId(row.getLong("lunch_recipe_id"));
        mealPlan.setDinnerRecipeId(row.getLong("dinner_recipe_id"));
        mealPlan.setSnackRecipeId(row.getLong("snack_recipe_id"));
        mealPlan.setDessertRecipeId(row.getLong("dessert_recipe_id"));
        return  mealPlan;
    }
}
