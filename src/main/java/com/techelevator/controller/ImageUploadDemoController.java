package com.techelevator.controller;

import com.techelevator.model.*;
import com.techelevator.uploads.UploadProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class ImageUploadDemoController
{
    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private IngredientDAO ingredientDAO;
    private UploadProvider uploadProvider;

    @Autowired
    public ImageUploadDemoController(UploadProvider uploadProvider)
    {
        this.uploadProvider = uploadProvider;
    }

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String uploadImage()
    {
        return "demos/upload";
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String uploadImage(String name, String type, @RequestParam(required = false) MultipartFile file, HttpSession session)
    {
        // 1. save recipe without image and get the recipe id
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setType(type);
        User user = (User) session.getAttribute("currentUser");
        recipe.setUserId(user.getUserId());
        recipeDAO.saveRecipe(recipe);


        if(file != null && !file.isEmpty())
        {
            try
            {
                // 2. get the id of the recipe - use it asx the image name

                String defaultFileName = recipe.getRecipeId().toString();// determine a unique file name


                // 3. save the image with the correct image name (the recipe id)
                String fileName = uploadProvider.uploadFile( file, "demo", defaultFileName );

                // 4. update the recipe record in the database with the image file name
                recipe.setImagePath(fileName);
                recipeDAO.updateRecipeImagePath(recipe.getRecipeId(), fileName);

            }
            catch(Throwable ex)
            {

            }
        }
        session.setAttribute("recipe", recipe);
        return "redirect:/addIngredients";
    }

}
