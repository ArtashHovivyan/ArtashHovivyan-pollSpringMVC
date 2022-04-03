package com.epam.pollSpringMVC.repository;

import com.epam.pollSpringMVC.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoRepository <T,E>{

    void addPhoto(T t);

    T getPhotoByUser(int id);

    boolean getByName(String imageName);

    int addPhoto(MultipartFile multipartFile, User user, String s) throws IOException;
}

