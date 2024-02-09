package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.uploads.UploadProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private IngredientDAO ingredientDAO;
    @Autowired
    private UploadProvider uploadProvider;


    @RequestMapping(path= "/recipes", method=RequestMethod.GET)
    public String displayRecipesPage(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }
        List<Recipe> recipes = recipeDAO.getAllRecipesByUserId(user.getUserId());
        session.setAttribute("recipes", recipes);
        session.setAttribute("currentRecipe", null);
        return "recipes";
    }

    @RequestMapping(path= "/recipes/details", method=RequestMethod.GET)
    public String displayRecipesDetails(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }
        List<Recipe> recipes = recipeDAO.getAllRecipesByUserId(user.getUserId());
        session.setAttribute("recipes", recipes);
        Long recipeId = Long.parseLong(request.getParameter("id"));
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        recipe.setIngredients(ingredientDAO.getIngredientList(recipeId));
        session.setAttribute("currentRecipe", recipe);
        return "recipes";

    }

    @RequestMapping(path ="/addRecipe", method= RequestMethod.GET)
    public String addRecipeForm(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }
        session.setAttribute("recipe", null);
        return "addRecipe";
    }

    @RequestMapping(path ="/addRecipe", method= RequestMethod.POST)
    public String submitRecipeForm(String name, String type, HttpSession session){
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setType(type);
        User user = (User) session.getAttribute("currentUser");
        recipe.setUserId(user.getUserId());

        recipeDAO.saveRecipe(recipe);
        session.setAttribute("recipe", recipe);
        return "redirect:/addIngredients";
    }

    @RequestMapping(path ="/addIngredients", method= RequestMethod.GET)
    public String addIngredientsForm(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }
        Recipe recipe = (Recipe) session.getAttribute("recipe");
        List<Ingredient> ingredients = ingredientDAO.getIngredientList(recipe.getRecipeId());
        recipe.setIngredients(ingredients);
        session.setAttribute("recipe", recipe);

        return "addIngredients";
    }

    @RequestMapping(path ="/addIngredients", method= RequestMethod.POST)
    public String submitIngredientsForm(Ingredient ingredient, HttpSession session){

        if (ingredientDAO.searchIngredient(ingredient.getName()) == null) {
            ingredientDAO.saveIngredient(ingredient);
        } else {
            String quantity = ingredient.getQuantity();
            ingredient = ingredientDAO.searchIngredient(ingredient.getName());
            ingredient.setQuantity(quantity);
        }
        Recipe recipe = (Recipe) session.getAttribute("recipe");
        ingredientDAO.saveIngredientToRecipe(ingredient, recipe.getRecipeId());

        return "redirect:/addIngredients";

    }

    @RequestMapping(path ="/addSteps", method= RequestMethod.GET)
    public String addStepsForm(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }
        return "addSteps";
    }

    @RequestMapping(path ="/addSteps", method= RequestMethod.POST)
    public String submitSteps(HttpSession session, String steps){
        Recipe recipe = (Recipe) session.getAttribute("recipe");
        recipeDAO.addRecipeSteps(steps, recipe.getRecipeId());
        //create Individual Recipe for profile
        return "redirect:/recipes/details?id="+recipe.getRecipeId();
    }

    @RequestMapping (path = "/recipes/delete", method = RequestMethod.POST)
    public String deleteRecipe(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        recipeDAO.deleteRecipe(id);
        return "redirect:/recipes";
    }

    @RequestMapping (path = "/recipes/modify", method = RequestMethod.GET)
    public String editRecipe(HttpServletRequest request, HttpSession session){
        Long id = Long.parseLong(request.getParameter("id"));
        Recipe recipe = recipeDAO.getRecipeById(id);
        session.setAttribute("recipe", recipe);
        return "addRecipe";
    }

    @RequestMapping (path = "/recipes/modify", method = RequestMethod.POST)
    public String editRecipeNameAndType(String name, String type, @RequestParam(required = false) MultipartFile file, HttpServletRequest request, HttpSession session){
        Long id = Long.parseLong(request.getParameter("id"));
        recipeDAO.updateRecipeName(name, id);
        recipeDAO.updateRecipeType(type, id);

        if(file != null && !file.isEmpty())
        {
            try
            {
                // 2. get the id of the recipe - use it asx the image name

                String defaultFileName = id.toString();// determine a unique file name


                // 3. save the image with the correct image name (the recipe id)
                String fileName = uploadProvider.uploadFile( file, "demo", defaultFileName );

                // 4. update the recipe record in the database with the image file name

                recipeDAO.updateRecipeImagePath(id, fileName);

            }
            catch(Throwable ex)
            {

            }
        }

        Recipe recipe = recipeDAO.getRecipeById(id);
        session.setAttribute("recipe", recipe);
        return "redirect:/addIngredients";
    }

    @RequestMapping(path = "/ingredients/delete", method = RequestMethod.POST)
    public String deleteIngredients(HttpServletRequest request) {
        Long recipeId = Long.parseLong(request.getParameter("recipe_id"));
        Long ingredientId = Long.parseLong(request.getParameter("ing_id"));
        ingredientDAO.deleteIngredientFromRecipe(ingredientId, recipeId);
        return "redirect:/addIngredients";
    }

}
