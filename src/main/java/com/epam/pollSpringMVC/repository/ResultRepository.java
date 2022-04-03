package com.epam.pollSpringMVC.repository;


import com.epam.pollSpringMVC.models.Result;

public interface ResultRepository<T, E> {
    void addResult(Result result, String pollId);

    Result getByPollId(String pollId);

}
