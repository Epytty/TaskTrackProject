package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.models.ProjectsEntity;
import com.tasktrack.TaskTrack.models.ProjectsService;
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

    @GetMapping("/projectsPage")
    public String projectsPage(Model model) {
        List<ProjectsEntity> project = projectsService.getAllProjects();
        model.addAttribute("project", project);
        return "projectsPage";
    }

    @GetMapping("/newProjectPage")
    public String newProjectPage(Model model) {
        ProjectsEntity project = new ProjectsEntity();
        model.addAttribute("project", project);
        return "newProjectPage";
    }
    @PostMapping
    public String createProject(@RequestParam String projectName, @RequestParam String projectDescription) {
        ProjectsEntity project = new ProjectsEntity(projectName, projectDescription);
        projectsService.saveProject(project);
        return "redirect:/projectsPage";
    }

    @GetMapping("/{project_id}")
    public Optional<ProjectsEntity> getProjectById(@PathVariable Long projectId) {
        return projectsService.getProjectById(projectId);
    }

    @DeleteMapping("/{project_id}")
    public void deleteUser(@PathVariable Long projectId) {
        projectsService.deleteProject(projectId);
    }
}
