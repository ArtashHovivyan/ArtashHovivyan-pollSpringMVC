package com.epam.pollSpringMVC.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {

    private int id;
    private String name;
    private String description;
    private List<Question> questions;
    private List<Result> results;
}
