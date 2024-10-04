package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectsPageController {

    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/projects")
    public String projectsPage(Model model) {
        List<ProjectsEntity> project = projectsService.getAllProjects();
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
    public String createProject(@RequestParam String name, @RequestParam String description) {
        projectsService.createProject(name, description);
        return "redirect:/projects";
    }

    @GetMapping("/project_id")
    public Optional<ProjectsEntity> getProjectById(@PathVariable Long id) {
        return projectsService.getProjectById(id);
    }

    @DeleteMapping("/project_id")
    public void deleteUser(@PathVariable Long id) {
    projectsService.deleteProject(id);
    }
}
