package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.services.impl.RolesServiceImpl;
import com.tasktrack.TaskTrack.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/adminPanel")
@RequiredArgsConstructor
public class AdminController {

    private final UsersService usersService;
    private final RolesServiceImpl rolesService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Head Director', 'Administrator')")
    public String adminPanelPage(Model model) {
        List<UsersEntity> user = usersService.getAllUsers();
        List<RolesEntity> role = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "adminPanel";
    }

    @PostMapping
    public String saveUser(@RequestParam Long id, @RequestParam String role, Model model) {
        UsersEntity user = usersService.getUserById(id);
        usersService.saveUserRole(id, role);
        model.addAttribute("user", user);
        return "redirect:/adminPanel";
    }
}
