package com.epam.pollSpringMVC.controller;


import com.epam.pollSpringMVC.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String indexGet(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            return "home";
        }
        model.addAttribute("massage", "Please login  !!!");
        return "login";
    }
}