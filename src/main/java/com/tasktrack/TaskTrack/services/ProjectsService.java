package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    public ProjectsRepository projectsRepository;

    public void saveProject(Long id, String name, String description) {
        ProjectsEntity project = getProjectById(id);
        project.setName(name);
        project.setDescription(description);
        projectsRepository.save(project);
    }

    public List<ProjectsEntity> getAllProjects() {
            return projectsRepository.findAll();
    }
    public ProjectsEntity getProjectById(Long id) {
        return projectsRepository.findById(id).orElse(null);
    }

    public void deleteProject(Long id) {
        projectsRepository.deleteById(id);
    }

    public void createProject(String name, String description) {
        ProjectsEntity project = new ProjectsEntity(name, description);
        projectsRepository.save(project);
    }

}