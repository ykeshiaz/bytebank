package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.techelevator.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.UserDAO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {

	private UserDAO userDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm(ModelMap model) {
		if(!model.containsAttribute("login")){
			model.addAttribute("login", new Login());
		}
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute Login login,
						BindingResult result,
						RedirectAttributes flash, HttpSession session) {
		flash.addFlashAttribute("login", login);

		if(userDAO.searchForUsernameAndPassword(login.getUserName(), login.getPassword())) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(login.getUserName()));
			return "redirect:/";
		}
		else {
			if (result.hasErrors()) {
				flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "login", result);
				return "redirect:/login";
			}
			flash.addAttribute("message", "User and/or password is invalid");
			return "redirect:/login";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
