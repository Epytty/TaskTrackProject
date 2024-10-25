package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectUsersEntity;
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
        return projectsRepository.findByProjectUsers_User_Id(userId);
    }

    public void createProject(Long userId, String name, String description) {
        UsersEntity owner = usersService.getUserById(userId);
        ProjectsEntity project = new ProjectsEntity(name, description);
        project.setOwner(owner);
        ProjectUsersEntity projectOwner = new ProjectUsersEntity();
        projectOwner.setProject(project);
        projectOwner.setUser(owner);
        projectOwner.setProjectRole("Owner");
        project.getProjectUsers().add(projectOwner);

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

    public List<ProjectsEntity> getProjectsByUser(Long id) {
        return projectsRepository.findByProjectUsers_User_Id(id);
    }

    public void addUser (Long projectId, String userDesignation) {
        ProjectsEntity project = getProjectById(projectId);
        UsersEntity user = null;
        if (userDesignation.contains("@")) {
            user = usersService.getByEmail(userDesignation);
        } else {
            user = usersService.getByUsername(userDesignation);
        }
        if (user != null) {
            ProjectUsersEntity projectUser  = new ProjectUsersEntity();
            projectUser.setProject(project);
            projectUser.setUser(user);
            projectUser.setProjectRole("Project User");
            project.getProjectUsers().add(projectUser );
            projectsRepository.save(project);
        }
    }

    public void excludeUser(Long projectId, Long userId) {
        ProjectsEntity project = getProjectById(projectId);
        ProjectUsersEntity projectUser  = project.getProjectUsers().stream()
                .filter(pu -> pu.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (projectUser  != null) {
            project.getProjectUsers().remove(projectUser);
            projectsRepository.save(project);
        }
    }
}