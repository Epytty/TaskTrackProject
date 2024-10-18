package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import com.tasktrack.TaskTrack.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects/{id}/usersList")
public class SelectedProjectController {

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private UsersService usersService;

    @GetMapping
    public String projectUsersListPage(@PathVariable(value = "id") Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        List<UsersEntity> user = usersService.getProjectUsers(project);
        model.addAttribute("project", project);
        model.addAttribute("user", user);
        return "usersList";
    }

    @GetMapping("/addUser")
    public String addUserPage(@PathVariable(value = "id") Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@PathVariable(value = "id") Long id,
                          @RequestParam String userDesignation) {
        projectsService.addUser(id, userDesignation);
        return "redirect:/projects/{id}/usersList";
    }

    @GetMapping("/exclude/{userId}")
    public String excludeUser(@PathVariable(value = "id") Long projectId,
                              @PathVariable(value = "userId") Long userId) {
        projectsService.excludeUser(projectId, userId);
        return "redirect:/projects/{id}/usersList";
    }
}
