package com.techelevator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCIngredientDAO implements IngredientDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCIngredientDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Ingredient searchIngredient(String name) {
        String select = "SELECT ingredient_id, name " +
                "FROM ingredient " +
                "WHERE name ILIKE ?";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, name);

        if (rows.next()) {
            long ingredientId = rows.getLong("ingredient_id");
            String ingredientName = rows.getString("name");


            Ingredient ingredient = new Ingredient();

            ingredient.setIngredientId(ingredientId);
            ingredient.setName(ingredientName);




            return ingredient;
        }

        return null;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
            ingredient.setIngredientId(getNextIngredientId());

            String insert = "INSERT INTO ingredient(ingredient_id, name) " +
                    "VALUES (?, ?);";

            jdbcTemplate.update(insert, ingredient.getIngredientId(), ingredient.getName());

    }

    public void saveIngredientToRecipe(Ingredient ingredient, Long recipeId) {
        String insert = "INSERT INTO recipe_ingredient(ingredient_id, recipe_id, quantity) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(insert, ingredient.getIngredientId(), recipeId, ingredient.getQuantity());
    }

    @Override
    public void deleteIngredientFromRecipe(Long ingredientId, Long recipeId) {
        String delete = "DELETE FROM recipe_ingredient WHERE ingredient_id = ? AND recipe_id = ?;";

        jdbcTemplate.update(delete, ingredientId, recipeId);
    }

    @Override
    public void editIngredientName(Long ingredientId, String name) {
        String update = "UPDATE ingredient SET name = ? WHERE ingredient_id = ?;";

        jdbcTemplate.update(update, name, ingredientId);
    }

    @Override
    public void editIngredientQuantity(Long ingredientId, Long recipeId, String quantity) {
        String update = "UPDATE recipe_ingredient SET quantity = ? WHERE ingredient_id = ? AND recipe_id = ?;";

        jdbcTemplate.update(quantity, ingredientId, recipeId);
    }

    private Long getNextIngredientId() {
        SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT nextval('ingredient_ingredient_id_seq') AS id;");
        if (row.next()) {
            return row.getLong("id");
        }
        return 0L;
    }

    @Override
    public List<Ingredient> getIngredientList(Long recipeId) {

        List<Ingredient> ingredients = new ArrayList<>();

        String select = "SELECT i.ingredient_id, i.name, quantity from recipe r " +
                "JOIN recipe_ingredient ri ON r.recipe_id = ri.recipe_id " +
                "JOIN ingredient i ON ri.ingredient_id = i.ingredient_id " +
                "WHERE ri.recipe_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(select, recipeId);

        while (rows.next()) {

            Ingredient ingredient = mapRowToIngredient(rows);

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rows) {

        long ingredientId = rows.getLong("ingredient_id");
        String name = rows.getString("name");
        String quantity = rows.getString("quantity");

        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientId(ingredientId);
        ingredient.setName(name);
        ingredient.setQuantity(quantity);

        return ingredient;
    }

}
