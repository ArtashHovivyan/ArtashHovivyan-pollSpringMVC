package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.ResultManager;
import com.epam.pollSpringMVC.models.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddResultController {
    ResultManager resultManager = new ResultManager();


    @PostMapping("/addResult")
    public String addResultPost(Model model, @RequestParam("explanation") String explanation, @RequestParam("min-score") String minScore,
                                @RequestParam("max-score") String maxScore, @RequestParam("pollID") String pollID) {

        Result result = new Result();
        result.setExplanation(explanation);
        result.setMinScore(Integer.parseInt(minScore));
        result.setMaxScore(Integer.parseInt(maxScore));
        resultManager.addResult(result, pollID);
        model.addAttribute("pollID", pollID);
        return"addAnswerAndQuestion";
    }
}
