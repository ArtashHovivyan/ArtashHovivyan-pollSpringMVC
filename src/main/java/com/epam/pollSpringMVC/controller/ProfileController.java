package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.PhotoManager;
import com.epam.pollSpringMVC.models.Photo;
import com.epam.pollSpringMVC.models.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class ProfileController {
    PhotoManager photoManager = new PhotoManager();
    String imageUploadDir = "C:\\Users\\Art\\IdeaProjects\\pollSpringMVC\\src\\main\\webapp\\static\\images";


    @GetMapping("/profile")
    public String profileGet(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            model.addAttribute("massage", "Please Login !!!");
            return "login";
        } else {

            user = (User) httpSession.getAttribute("user");
            System.out.println(user);
        }
       Photo photo= photoManager.getPhotoByUser(user.getPhoto_id());
        model.addAttribute("user", user);
        model.addAttribute("photo", photo);
        return "profile";
    }


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("imageUrl") String imageUrl) {
        return photoManager.getImage(imageUrl);
    }



}
