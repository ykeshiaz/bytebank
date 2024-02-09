package com.techelevator.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private IngredientDAO ingredientDAO;
    @Autowired
    private MealPlanDAO mealPlanDAO;

    @RequestMapping(path= {"/", "/home"}, method=RequestMethod.GET)
    public String displayHomePage(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if(user!=null) {
            List<Recipe> recipes = recipeDAO.getAllRecipesByUserId(user.getUserId());
            session.setAttribute("recipes", recipes);
        }
        return "homePage";
    }

    @RequestMapping(path= "/groceryList", method=RequestMethod.GET)
    public String displayGroceryListPage(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("currentUser");
        //checking if viewer is logged in, can this be made into function?
        if(user==null) {
            return "redirect:/users/new";
        }
        String mp = request.getParameter("mp_id");
        String recipe_id = request.getParameter("id");
        List<Ingredient> groceries = new ArrayList<>();
        if(mp!=null) {
            Long mealPlanId = Long.parseLong(mp);
            MealPlan mealPlan = mealPlanDAO.getMealPlanById(mealPlanId);
//            Recipe bkRecipe = recipeDAO.getRecipeById(mealPlan.getBreakfastRecipeId());
            groceries.addAll(ingredientDAO.getIngredientList(mealPlan.getBreakfastRecipeId()));
//            Recipe lunRecipe = recipeDAO.getRecipeById(mealPlan.getLunchRecipeId());
            groceries.addAll(ingredientDAO.getIngredientList(mealPlan.getLunchRecipeId()));
//            Recipe dinRecipe = recipeDAO.getRecipeById(mealPlan.getDinnerRecipeId());
            groceries.addAll(ingredientDAO.getIngredientList(mealPlan.getDinnerRecipeId()));
//            Recipe snkRecipe = recipeDAO.getRecipeById(mealPlan.getSnackRecipeId());
            groceries.addAll(ingredientDAO.getIngredientList(mealPlan.getSnackRecipeId()));
//            Recipe dessrtRecipe = recipeDAO.getRecipeById(mealPlan.getDessertRecipeId());
            groceries.addAll(ingredientDAO.getIngredientList(mealPlan.getDessertRecipeId()));
        }

        if(recipe_id!=null) {
            Long recipeId = Long.parseLong(request.getParameter("id"));
//            Recipe recipe = recipeDAO.getRecipeById(recipeId);
            groceries = ingredientDAO.getIngredientList(recipeId);
        }
        session.setAttribute("groceries", groceries);
        return "groceryList";
    }
    @RequestMapping(path= "/about", method=RequestMethod.GET)
    public String displayAboutPage() {
        return "aboutByteBank";
    }

}
