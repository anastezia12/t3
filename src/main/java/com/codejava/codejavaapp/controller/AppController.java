package com.codejava.codejavaapp.controller;

import com.codejava.codejavaapp.domain.Role;
import com.codejava.codejavaapp.domain.User;
import com.codejava.codejavaapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSingUpForm(Model model, @ModelAttribute("user")  User user) {
        model.addAttribute("user", new User());
        Date date = new Date();
        user.setLastLogin(date.toString());
        return "singupForm";
    }



    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Date date = new Date();
        user.setFirstLogin(date.toString());
        user.setLastLogin(date.toString());
        user.setBlock(Role.Unblock);


        userRepository.save(user);
        return "registerSucces";
    }

    @GetMapping("/users")
    public String viewUsersList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("listUsers", userList);
        return "users";
    }


}

