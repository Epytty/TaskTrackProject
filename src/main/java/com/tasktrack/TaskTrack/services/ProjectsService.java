package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {

    @Autowired
    public ProjectsRepository projectsRepository;

    public ProjectsEntity saveProject(ProjectsEntity project) {
        return projectsRepository.save(project);
    }

    public List<ProjectsEntity> getAllProjects() {
            return projectsRepository.findAll();
    }
    public Optional<ProjectsEntity> getProjectById(Long id) {
        return projectsRepository.findById(id);
    }

    public void deleteProject(Long id) {
        projectsRepository.deleteById(id);
    }

    public void createProject(String name, String description) {
        ProjectsEntity project = new ProjectsEntity(name, description);
        projectsRepository.save(project);
    }
}