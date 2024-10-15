package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.RolesRepository;
import com.tasktrack.TaskTrack.repositories.UsersRepository;
import com.tasktrack.TaskTrack.services.RolesService;
import com.tasktrack.TaskTrack.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/register")
    public String registerUserPage(Model model) {
        model.addAttribute("user", new UsersEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        usersService.registerUser(username, password);
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

    @GetMapping("/adminPanel")
    @PreAuthorize("hasAnyAuthority('Head Director', 'Administrator', 'project Manager')")
    public String adminPanelPage(Model model) {
        List<UsersEntity> user = usersService.getAllUsers();
        List<RolesEntity> role = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "adminPanel";
    }

    @PostMapping("/adminPanel")
    public String saveUser(@RequestParam Long id, @RequestParam String role, Model model) {
        UsersEntity user = usersService.getUserById(id);
        usersService.saveUserRole(id, role);
        model.addAttribute("user", user);
        return "redirect:/adminPanel";
    }

}
