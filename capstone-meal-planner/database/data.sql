-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
INSERT INTO app_user (user_name, password, salt)
VALUES ('jas', '7Q1K9OTsUF646alzO2aL9g==', 'i06yfM4fci1ml8aFTT0aYFr86FeRG6iAMkqah7VgQO7SIj+//pOCSBwdmktvhmDnnBDy8OcrxPmfQ1Iv//fimmT8dW4ywlhzDT7TNhmsQraSY2e4WNXkM4+y7uyEPi7uIJe4xt4fosEn/8WCwdD2Ewtk8iJDS6JYINUoKQsZQ+0=')
,('liddy', 'wavfsO0baizExP0Jsoolug==', 'vVhFElGjAR/RzuF4KD8ctkwh+v96QUF13bLjeYH2l3QBj9E7zEzzd89yKYwdDZok9lhZ8PWD92B33rqwW29iM1F0V7PhJCWBABHGNgEePgS4N2HzvpHJy25pKJYCm0Yb+XGYwfj3QTQ/OM1SGMiDNhKhhmNzgGzdZwoJgj8eGGM=')
,('sie', 'hKBbH3lqljy5o3ZRP6T5GQ==', 'asp5NnLSse7a3oILgWxQCaTp++k8c/pTqhO46Ez8G8shQje/3xmncN4yV1MeQjTgIsFBBpsfrHTMndKmuQTg0vR3ru2hW2Rku0JIluKCt00OPotgzoObdC50wGnG9ZvfbHXqQoWjhKw687Rh/ABUcO8moF0a8hLfSFvRSMsNsb4=')
,('yke', 'qZfbigpybhqO+Gf7uF1/Iw==', 'Cw1/cbvWky29dcHy+fWRMWGODXNWYS/MAHPYi3gyykaOdc6cFNO18+uteUaT0pxaS+/nTQJkQrkAWQq2LgIui7RHBQbSy/pXZwA5kcF82A3aR93AYIHss+7t6hYffW9e6WAac2KBakBAL/hmiPbUKTWu73DadisYP3i6shTE6JI=')
,('sel', 'Em2OQai2rV8xbaOby4OntQ==', 'Vhpdu+MEwD1h8ELGhqLvkvKldfd9QDgYpYCD5vkR+fcaO5kZ92tUMK+yGAbwRNen0ckRJm/ZXGc9QrmYiZhQ84/TGw4vucBudC809R8NiO2AIOdcMkXgDQi5HzTN3JZxOitsAcqhVWqQHKIeG2n2rO7riwxDu+zqoBYk/vXoQEw=');

INSERT INTO recipe (name, type, steps, user_id)
VALUES ('PB&J', 'Breakfast', '1.) Spread peanut butter on one slice of bread 2.) Spread jelly on another slice of bread 3.) Put the two pieces of bread together to form a sandwich.', '2');


INSERT INTO ingredient(name)
VALUES ('Bread'),('Peanut Butter'),('Jelly');

INSERT INTO recipe_ingredient (ingredient_id, recipe_id, quantity)
VALUES (1,1,'2 slices'), (2,1, '1 tbsp'), (3,1,'1 tbsp');
COMMIT;



