package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.PollManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageTestController {
    PollManager pollManager = new PollManager();


    @GetMapping("/pageTest")
            public String pageTestGet(Model model) {
        model.addAttribute("polls",pollManager.findAll());
        return "pageTest";
    }
}
