package com.epam.pollSpringMVC.repository;

public interface UserRepository<T,E> {

    void registerUser(T t);

    T login(String email, String password);

    boolean findByEmail(String email);
}
