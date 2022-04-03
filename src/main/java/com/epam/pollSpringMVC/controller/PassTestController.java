package com.epam.pollSpringMVC.controller;


import com.epam.pollSpringMVC.manager.PollManager;
import com.epam.pollSpringMVC.manager.QuestionManager;
import com.epam.pollSpringMVC.models.Answer;
import com.epam.pollSpringMVC.models.Poll;
import com.epam.pollSpringMVC.models.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PassTestController {

    PollManager pollManager = new PollManager();
    QuestionManager questionManager = new QuestionManager();

    @GetMapping("/passTest")
    public String passTestGet(Model model, @RequestParam("id")String id){
        Poll poll = pollManager.getByID(id);
        List<Question> questionList = questionManager.getByPollId(poll.getId());
        poll.setQuestions(questionList);
        model.addAttribute("poll", poll);
        return "passTest";
    }
}
