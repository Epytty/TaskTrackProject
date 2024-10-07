package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.TasksEntity;
import com.tasktrack.TaskTrack.services.ProjectsService;
import com.tasktrack.TaskTrack.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects/{id}")
public class TasksController {

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private TasksService tasksService;

    @GetMapping
    public String selectProject(@PathVariable(value = "id") Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        List<TasksEntity> task = tasksService.getAllTasksByProjectId(id);
        model.addAttribute("task", task);
        return "project";
    }

    @GetMapping("/newTask")
    public String newTaskPage(@PathVariable Long id, Model model) {
        ProjectsEntity project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("task", new TasksEntity());
        return "newTask";
    }

    @PostMapping("/newTask")
    public String createTask(@PathVariable(value = "id") Long id,
                             @RequestParam String name,
                             @RequestParam String description) {
        tasksService.createTask(id, name, description);
        return "redirect:/projects/{id}";
    }

    @GetMapping("/editTask")
    public String editTaskPage(@PathVariable(value = "id") Long id, Model model) {
        TasksEntity task = tasksService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTask";
    }
}
