package com.epam.pollSpringMVC.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private int id;
    private String text;
    private List<Answer> answers;
    private int pollId;
}
