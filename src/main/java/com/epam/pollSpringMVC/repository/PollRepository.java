package com.epam.pollSpringMVC.repository;

import java.util.List;

public interface PollRepository<T, E> {

    int addPoll(T pool);

    List<T> findAll();

    T getByID(String id);
}
