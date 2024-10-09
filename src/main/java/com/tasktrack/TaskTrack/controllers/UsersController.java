package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String registerUserPage(Model model) {
        model.addAttribute("user", new UsersEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String roles) {
        usersService.registerUser(username, password, roles);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }
}
