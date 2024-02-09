package com.techelevator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCRecipeDAO implements RecipeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCRecipeDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveRecipe(Recipe recipe) {

        recipe.setRecipeId(getNextRecipeId());

        String insert = "INSERT INTO recipe (recipe_id, name, type, user_id) VALUES (?, ?, ?, ?);";

        jdbcTemplate.update(insert, recipe.getRecipeId(), recipe.getName(), recipe.getType(), recipe.getUserId());
    }

    private Long getNextRecipeId() {
        SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT nextval('recipe_recipe_id_seq') AS id;");
        if(row.next()) {
            return row.getLong("id");
        }
        return 0L;
    }
    public void updateRecipeImagePath(Long recipeId, String imagePath){
        String update = "UPDATE recipe SET image_path = ? WHERE recipe_id = ?;";

        jdbcTemplate.update(update, imagePath, recipeId);

    }


    @Override
    public List<Recipe> searchRecipe(String recipeName) {

        List<Recipe> recipes = new ArrayList<>();

        String select = "SELECT recipe_id, name, type, steps, user_id, image_path FROM recipe WHERE name ILIKE '%'||?||'%';";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, recipeName);

        while (rows.next()) {

            Recipe recipe = mapRowToRecipe(rows);

            recipes.add(recipe);
        }

        return recipes;
    }

    @Override
    public void addRecipeSteps(String steps, Long recipeId) {

        String update = "UPDATE recipe SET steps = ? WHERE recipe_id = ?;";

        jdbcTemplate.update(update, steps, recipeId);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {

        String select = "SELECT recipe_id, name, type, steps, user_id, image_path FROM recipe WHERE recipe_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, recipeId);

        if (rows.next()) {

            Recipe recipe = mapRowToRecipe(rows);

            return recipe;
        }

        return null;
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(Long userId) {
        List<Recipe> recipes = new ArrayList<>();

        String select = "SELECT recipe_id, name, type, steps, user_id , image_path FROM recipe WHERE user_id = ? " +
                "ORDER BY name;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, userId);

        while (rows.next()) {
            Recipe recipe = mapRowToRecipe(rows);

            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public void updateRecipeName(String newName, Long recipeId) {

        String update = "UPDATE recipe SET name = ? WHERE recipe_id = ?;";

        jdbcTemplate.update(update, newName, recipeId);

    }

    @Override
    public void updateRecipeType(String type, Long recipeId) {

        String update = "UPDATE recipe SET type = ? WHERE recipe_id = ?;";

        jdbcTemplate.update(update, type, recipeId);
    }


    @Override
    public void deleteRecipe(Long recipeId) {
        String update = "UPDATE meal_plan SET breakfast_recipe_id = NULL WHERE breakfast_recipe_id = ? ;" +
                "UPDATE meal_plan SET lunch_recipe_id = NULL WHERE lunch_recipe_id = ? ; " +
                "UPDATE meal_plan SET dinner_recipe_id = NULL WHERE dinner_recipe_id = ? ; " +
                "UPDATE meal_plan SET snack_recipe_id = NULL WHERE snack_recipe_id = ? ; " +
                "UPDATE meal_plan SET dessert_recipe_id = NULL WHERE dessert_recipe_id = ? ; ";
        jdbcTemplate.update(update, recipeId, recipeId, recipeId, recipeId, recipeId);
        String delete = "DELETE FROM recipe_ingredient WHERE recipe_id = ?;" +
                "DELETE FROM recipe WHERE recipe_id = ?;";

        jdbcTemplate.update(delete, recipeId, recipeId);
    }

    private Recipe mapRowToRecipe(SqlRowSet rows) {

        long recipeId = rows.getLong("recipe_id");
        String name = rows.getString("name");
        String type = rows.getString("type");
        String steps = rows.getString("steps");
        long userId = rows.getLong("user_id");
        String imagePath = rows.getString("image_path");

        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setName(name);
        recipe.setType(type);
        recipe.setSteps(steps);
        recipe.setUserId(userId);
        recipe.setImagePath(imagePath);

        return recipe;
    }

}
