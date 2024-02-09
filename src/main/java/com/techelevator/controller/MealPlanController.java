package com.techelevator.controller;

import com.techelevator.model.MealPlan;
import com.techelevator.model.MealPlanDAO;
import com.techelevator.model.RecipeDAO;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MealPlanController {

    @Autowired
    private MealPlanDAO mealPlanDAO;

    @Autowired
    public MealPlanController(MealPlanDAO mealPlanDAO) {
        this.mealPlanDAO = mealPlanDAO;
    }

    @RequestMapping(path= "/mealPlan", method= RequestMethod.GET)
    public String displayMealPlanPage(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");
        if(user==null) {
            return "redirect:/users/new";
        }

        if(date== null) date = LocalDate.now();

        // make sure we start on Sunday
        // sunday = 7
        // if not Sunday then create startDate for sunday date.minusDays
        if(date.getDayOfWeek().getValue()<7) {
            date = date.minusDays(date.getDayOfWeek().getValue());
        }


        LocalDate startDate = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        List<String> weekDates = new ArrayList<>();
        List<DayOfWeek> dayNames = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dayNames.add(date.getDayOfWeek());
            String dateFormat = formatter.format(date);
            weekDates.add(dateFormat);
            date = date.plusDays(1);
        }
        LocalDate endDate = date.minusDays(1);

        session.setAttribute("weekDates", weekDates);
        session.setAttribute("dayNames", dayNames);

        List<MealPlan> mealPlans = mealPlanDAO.getAllMealPlansByUserId(user.getUserId(), startDate, endDate);
        session.setAttribute("mealPlans", mealPlans);

        return "mealPlan";
    }


    @RequestMapping(path= "/addMealPlan", method= RequestMethod.POST)
    public String addMealPlan(@RequestParam String selectRecipe, HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        MealPlan mealPlan = mealPlanDAO.getMealPlanById(id);

        String mealType = request.getParameter("type");

        if(!selectRecipe.equals("--")){
            Long recipeId = Long.parseLong(selectRecipe);
            mealPlanDAO.updateMealPlan(mealPlan.getMealPlanId(), mealType, recipeId);
        }
        return "redirect:/mealPlan?date="+mealPlan.getDate();
    }

    @RequestMapping(path= "/deleteMeal", method= RequestMethod.POST)
    public String deleteMeal(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        MealPlan mealPlan = mealPlanDAO.getMealPlanById(id);

        String mealType = request.getParameter("type");
        mealPlanDAO.updateMealPlan(id, mealType, null);

        return "redirect:/mealPlan?date="+mealPlan.getDate();
    }

}
