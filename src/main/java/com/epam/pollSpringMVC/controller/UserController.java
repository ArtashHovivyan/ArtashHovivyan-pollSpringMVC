package com.epam.pollSpringMVC.controller;

import com.epam.pollSpringMVC.manager.PhotoManager;
import com.epam.pollSpringMVC.manager.UserManager;
import com.epam.pollSpringMVC.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    UserManager userManager = new UserManager();
    PhotoManager photoManager = new PhotoManager();

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute User user, @RequestParam("image") MultipartFile multipartFile,
                               @RequestParam("imageName") String imageName) throws IOException {
        if (multipartFile == null) {
            assert false;
            model.addAttribute("massage", "Photo is not loaded  !!!");
            return "redirect:/login";
        }
        if (userManager.findByEmail(user.getEmail())) {
            user.setPhoto_id(photoManager.addPhoto(multipartFile, user, imageName + ".jpg"));
            userManager.registerUser(user);
            return "redirect:/login";
        }
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "login";

    }

    @PostMapping("/login")
    public String loginGet(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession httpSession) {
        User user = userManager.login(email, password);
        if (user.getEmail() != null) {
            httpSession.setAttribute("user", user);
            if (httpSession.getAttribute("user") == user) {
                return "redirect:/home";
            }
        }
        model.addAttribute("massage", "Please Logined");
        return "login";

    }
}
