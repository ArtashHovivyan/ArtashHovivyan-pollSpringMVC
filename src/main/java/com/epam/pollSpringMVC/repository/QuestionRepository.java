package com.epam.pollSpringMVC.repository;


import com.epam.pollSpringMVC.models.Question;

import java.util.List;

public interface QuestionRepository <T, E>{

    int addQuestions(T question, String id);

    List<Question> getByPollId(int id);
}
