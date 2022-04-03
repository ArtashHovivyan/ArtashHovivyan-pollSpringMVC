package com.epam.pollSpringMVC.controller;


import com.epam.pollSpringMVC.manager.PollManager;
import com.epam.pollSpringMVC.models.Poll;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PollController {
    PollManager pollManager = new PollManager();

    @GetMapping("/addPoll")
    public String addPollGet() {
        return "addPoll";
    }

    @PostMapping("/addPoll")
    public String addPollPost(@RequestParam("name") String name, @RequestParam("description") String description , Model model) {
        Poll poll = new Poll();
        poll.setName(name);
        poll.setDescription(description);
        int pollId = pollManager.addPoll(poll);
        model.addAttribute("pollID",pollId);
        return "addResult";
    }

}
