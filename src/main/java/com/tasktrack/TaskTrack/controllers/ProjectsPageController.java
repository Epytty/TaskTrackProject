package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.Project;
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

    @GetMapping("/projectsPage")
    public String projectsPage(Model model) {
        List<Project> project = projectsService.getAllProjects();
        model.addAttribute("project", project);
        return "projectsPage";
    }

//    @GetMapping("/newProjectPage")
//    public String newProjectPage(Model model) {
//        ProjectsEntity project = new ProjectsEntity();
//        model.addAttribute("project", project);
//        return "newProjectPage";
//    }
    @PostMapping("/projectsPage")
    public String createProject(@RequestParam String name, @RequestParam String description) {
        Project project = new Project(name, description);
        projectsService.saveProject(project);
        return "projectsPage";
    }

    @GetMapping("/{project_id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectsService.getProjectById(id);
    }

    @DeleteMapping("/{project_id}")
    public void deleteUser(@PathVariable Long id) {
        projectsService.deleteProject(id);
    }
}
