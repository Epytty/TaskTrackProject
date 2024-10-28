package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectUsersEntity;
import com.tasktrack.TaskTrack.entities.ProjectsEntity;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import com.tasktrack.TaskTrack.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAuthority('Owner')")
    public String projectUsersListPage(@PathVariable(value = "id") Long id, Model model, Authentication authentication) {
        UsersEntity currentUser  = usersService.getByUsername(authentication.getName());

        if (!projectsService.isUserOwner(id, currentUser.getId())) {
            return "redirect:/error";
        }

        ProjectsEntity project = projectsService.getProjectById(id);
        List<ProjectUsersEntity> projectUsers = project.getProjectUsers();
        model.addAttribute("project", project);
        model.addAttribute("projectUsers", projectUsers);
        return "usersList";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Owner')")
    public String updateUserRole(@PathVariable(value = "projectId") Long projectId,
                                 @RequestParam Long userId,
                                 @RequestParam String newRole) {
        // Проверка, является ли текущий пользователь владельцем проекта
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity currentUser  = usersService.getByUsername(authentication.getName());

        if (!projectsService.isUserOwner(projectId, currentUser .getId())) {
            return "redirect:/accessDenied";
        }
        ProjectsEntity project = projectsService.getProjectById(projectId);
        ProjectUsersEntity projectUser = project.getProjectUsers().stream()
                .filter(pu -> pu.getUser ().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (projectUser  != null) {
            projectUser.setProjectRole(newRole);
            projectsService.saveProjectChanges(project);
        }
        return "redirect:/projects/{projectId}/usersList";
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
