package com.epam.pollSpringMVC.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private int id;

    private String explanation;

    private int minScore;

    private int maxScore;
}
