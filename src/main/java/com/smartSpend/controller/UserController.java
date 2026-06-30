package com.smartSpend.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartSpend.entity.User;
import com.smartSpend.service.TransactionService;
import com.smartSpend.service.UserService;

@Controller
public class UserController {

	
	    @Autowired
	    private UserService userService;
	    @Autowired
	    private TransactionService transactionService;

	    @GetMapping("/")
	    public String home() {
	        return "home";
	    }

	    @GetMapping("/home")
	    public String homePage() {
	        return "home";
	    }

	    @GetMapping("/register")
	    public String showRegisterPage(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute("user") User user) {
	        userService.registerUser(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String showLoginPage() {
	        return "login";
	    }
	    
	    @GetMapping("/dashboard")
	    public String dashboard(Model model, Principal principal) {
	        Map<String, Double> summary = transactionService.getDashboardSummary(principal.getName());
	        Map<String, Double> categoryWiseExpense = transactionService.getCategoryWiseExpense(principal.getName());
	        Map<String, Object> lastThreeMonths = transactionService.getLastThreeMonthsData(principal.getName());

	        model.addAttribute("summary", summary);
	        model.addAttribute("categoryExpense", categoryWiseExpense);
	        model.addAttribute("lastThreeMonths", lastThreeMonths);
	        model.addAttribute("username", principal.getName());
	        return "dashboard";
	    }
	    
	  
	}
	
	

