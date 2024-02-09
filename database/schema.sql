
-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
                          id SERIAL PRIMARY KEY,
                          user_name varchar(32) NOT NULL UNIQUE,
                          password varchar(32) NOT NULL,
                          salt varchar(255) NOT NULL
);

CREATE TABLE recipe (
                        recipe_id SERIAL PRIMARY KEY,
                        name varchar(32) NOT NULL,
                        type varchar(32),
                        steps text,
                        user_id int,
                        image_path varchar(255) NULL -- /uploads/user/1.jpg
);

CREATE TABLE recipe_ingredient (
                                   ingredient_id int,
                                   recipe_id int,
                                   quantity varchar(32)
);

CREATE TABLE meal_plan (
                           mealplan_id serial PRIMARY KEY,
                           user_id int,
                           date date,
                           breakfast_recipe_id int NULL,
                           lunch_recipe_id int NULL,
                           dinner_recipe_id int NULL,
                           snack_recipe_id int NULL,
                           dessert_recipe_id int NULL


);

CREATE TABLE ingredient (
                            ingredient_id serial PRIMARY KEY,
                            name varchar(32)
);

ALTER TABLE recipe
    ADD FOREIGN KEY (user_id)
        REFERENCES app_user(id);

ALTER TABLE recipe_ingredient
    ADD FOREIGN KEY (recipe_id)
        REFERENCES recipe(recipe_id);

ALTER TABLE recipe_ingredient
    ADD FOREIGN KEY (ingredient_id)
        REFERENCES ingredient(ingredient_id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (user_id)
        REFERENCES app_user(id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (breakfast_recipe_id)
        REFERENCES recipe(recipe_id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (lunch_recipe_id)
        REFERENCES recipe(recipe_id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (dinner_recipe_id)
        REFERENCES recipe(recipe_id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (snack_recipe_id)
        REFERENCES recipe(recipe_id);

ALTER TABLE meal_plan
    ADD FOREIGN KEY (dessert_recipe_id)
        REFERENCES recipe(recipe_id);

COMMIT;

DROP TABLE recipe;
