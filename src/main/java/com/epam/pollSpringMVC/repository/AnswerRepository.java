package com.epam.pollSpringMVC.repository;


import com.epam.pollSpringMVC.models.Answer;

import java.util.List;

public interface AnswerRepository <T, E> {


    void addAnswer(T answer, int id);

    List<Answer> findByQuestionId(int id);
}
