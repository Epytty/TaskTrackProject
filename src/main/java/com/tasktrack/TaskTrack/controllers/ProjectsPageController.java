package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import com.tasktrack.TaskTrack.services.ProjectsService;
import com.tasktrack.TaskTrack.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectsPageController {

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private UsersService usersService;

    @GetMapping
    public String getUserProjects(Model model, Principal principal) {
        UsersEntity user = usersService.getByUsername(principal.getName());
        List<ProjectsEntity> project = projectsService.getProjectsByUser(user.getId());
        model.addAttribute("project", project);
        return "projects";
    }

    @GetMapping("/newProject")
    public String newProjectPage(Model model) {
        ProjectsEntity project = new ProjectsEntity();
        model.addAttribute("project", project);
        return "newProject";
    }

    @PostMapping("/newProject")
    public String createProject(@RequestParam String name,
                                @RequestParam String description,
                                Principal principal) {
        UsersEntity user = usersService.getByUsername(principal.getName());
        projectsService.createProject(user.getId(), name, description);
        return "redirect:/projects";
    }


    @GetMapping("/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/{id}/edit")
    public String saveEditProject(@PathVariable(value = "id") Long id,
                                  @RequestParam String name,
                                  @RequestParam String description) {
        projectsService.saveProject(id, name, description);
        return "redirect:/projects/{id}/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectsService.deleteProject(id);
        return "redirect:/projects";
    }
}
