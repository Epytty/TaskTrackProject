package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/newProjectPage")
    public String createProject(@RequestParam String name, @RequestParam String description) {
        ProjectsEntity project = new ProjectsEntity(name, description);
        projectsService.saveProject(project);
        return "redirect:/projectsPage";
    }

//    @GetMapping("/project_id")
//    public Optional<ProjectsEntity> getProjectById(@PathVariable Long id) {
//        return projectsService.getProjectById(id);
//    }
//
//    @DeleteMapping("/project_id")
//    public void deleteUser(@PathVariable Long id) {
//        projectsService.deleteProject(id);
//    }
}
