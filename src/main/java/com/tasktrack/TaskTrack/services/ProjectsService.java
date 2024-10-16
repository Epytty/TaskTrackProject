package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    public ProjectsRepository projectsRepository;

    @Autowired
    public UsersService usersService;

    public List<ProjectsEntity> getAllProjects(Long userId) {
        return projectsRepository.findByUserId(userId);
    }

    public void createProject(Long userId, String name, String description) {
        UsersEntity owner = usersService.getUserById(userId);
        ProjectsEntity project = new ProjectsEntity(name, description);
        project.setOwner(owner);
        project.getUser().add(owner);
        projectsRepository.save(project);
    }

    public ProjectsEntity getProjectById(Long id) {
        return projectsRepository.findById(id).orElse(null);
    }

    public void saveProject(Long id, String name, String description) {
        ProjectsEntity project = getProjectById(id);
        project.setName(name);
        project.setDescription(description);
        projectsRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectsRepository.deleteById(id);
    }

    public List<ProjectsEntity> getProjectsByUser (Long userId) {
        return projectsRepository.findByUserId(userId);
    }

    public void addUser(Long projectId, String username) {
        ProjectsEntity project = getProjectById(projectId);
        UsersEntity user = usersService.getByUsername(username);
        project.getUser().add(user);
        user.getProject().add(project);
        projectsRepository.save(project);
    }

}