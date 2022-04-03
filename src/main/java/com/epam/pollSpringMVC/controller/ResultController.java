package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.ResultManager;
import com.epam.pollSpringMVC.models.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    ResultManager resultManager = new ResultManager();

    @PostMapping("/result")

    public String resultPost(Model model, @RequestParam("id") String id, @RequestParam("weights") String[] weights) {
        int x = 0;
        String[] values = weights;
        int[] s = new int[values.length];
        if (s.length<3){
            model.addAttribute("massagess", "you didn't complete the test\n" +
                    "take the test again...");
            return "passTest";
        }
        for (int i = 0; i < values.length; i++) {
            s[i] = Integer.parseInt(values[i]);
            x = x + s[i];
        }
        Result result = resultManager.getByPollId(id);
        String b = result.getExplanation();
        String[] c = b.split(",");

        if (result.getMaxScore() == x) {
            model.addAttribute("massage", c[0]);
            model.addAttribute("massage1", c[1]);
            return "result";

        } else if (x >= result.getMaxScore() / 2) {
            model.addAttribute("massage", c[0]);
            model.addAttribute("massage1", c[2]);
            return "result";

        } else if (x < result.getMaxScore() / 2 && x < result.getMinScore()) {
            model.addAttribute("massage", c[0]);
            model.addAttribute("massage1", c[3]);
            return "result";

        } else if (x == result.getMinScore()) {
            model.addAttribute("massage", c[0]);
            model.addAttribute("massage1", c[4]);
            return "result";
        }
        return "home";
    }


}
