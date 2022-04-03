package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.AnswerManager;
import com.epam.pollSpringMVC.manager.PollManager;
import com.epam.pollSpringMVC.manager.QuestionManager;
import com.epam.pollSpringMVC.models.Answer;
import com.epam.pollSpringMVC.models.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnswerController {
    PollManager pollManager = new PollManager();
    AnswerManager answerManager = new AnswerManager();
    QuestionManager questionManager = new QuestionManager();

    @GetMapping("/addAnswer")
    public String addAnswerGet(Model model) {
        model.addAttribute("polls", pollManager.findAll());
        return "addAnswerAndQuestion";
    }

    @PostMapping("/addAnswer")
    public String addAnswerPost(Model model, @RequestParam("pollID") String pollID, @RequestParam("question") String question,
                                @RequestParam("answer") String[] answers1, @RequestParam("weight") String[] weight) {
        List<Answer> answers = new ArrayList<>();
        Question question1 = new Question();
        for (int i = 0; i < answers1.length; i++) {
            Answer answer = new Answer();
            answer.setText(answers1[i]);
            answer.setWeight(Integer.parseInt(weight[i]));
            answers.add(answer);
            question1.setText(question);
            question1.setAnswers(answers);
        }
        int questionId = questionManager.addQuestions(question1, pollID);
        for (Answer answer : answers) {
            answerManager.addAnswer(answer, questionId);
        }
        model.addAttribute("pollID", pollID);
        return "addAnswerAndQuestion";

    }
}
