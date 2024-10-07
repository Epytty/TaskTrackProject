package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
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

    @GetMapping("/projects/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/projects/{id}/edit")
    public String saveEditProject(@PathVariable(value = "id") Long id,
                                  @RequestParam String name,
                                  @RequestParam String description) {
        projectsService.saveProject(id, name, description);
        return "redirect:/projects/{id}";
    }

    @GetMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectsService.deleteProject(id);
        return "redirect:/projects";
    }
}
