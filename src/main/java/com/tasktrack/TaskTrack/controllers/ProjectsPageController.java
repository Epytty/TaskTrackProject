package com.tasktrack.TaskTrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectsPageController {

    @GetMapping("/projectsPage")
    public String projectsPage(Model model) {
        return "projectsPage";
    }
}
