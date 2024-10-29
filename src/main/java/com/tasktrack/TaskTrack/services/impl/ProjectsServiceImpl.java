package com.tasktrack.TaskTrack.services.impl;

import com.tasktrack.TaskTrack.entities.ProjectUsersEntity;
import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import com.tasktrack.TaskTrack.services.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final UsersServiceImpl usersServiceImpl;

    @Override
    public List<ProjectsEntity> getAllProjects(Long userId) {
        return projectsRepository.findByProjectUsers_User_Id(userId);
    }

    @Override
    public void createProject(Long userId, String name, String description) {
        UsersEntity owner = usersServiceImpl.getUserById(userId);
        ProjectsEntity project = new ProjectsEntity(name, description);
        project.setOwner(owner);
        ProjectUsersEntity projectOwner = new ProjectUsersEntity();
        projectOwner.setProject(project);
        projectOwner.setUser (owner);
        projectOwner.setProjectRole("Owner");
        project.getProjectUsers().add(projectOwner);
        projectsRepository.save(project);
    }

    @Override
    public ProjectsEntity getProjectById(Long id) {
        return projectsRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProject(Long id, String name, String description) {
        ProjectsEntity project = getProjectById(id);
        if (project != null) {
            project.setName(name);
            project.setDescription(description);
            projectsRepository.save(project);
        }
    }

    @Override
    public void saveProjectChanges(ProjectsEntity project) {
        projectsRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectsRepository.deleteById(id);
    }

    @Override
    public List<ProjectsEntity> getProjectsByUser (Long id) {
        return projectsRepository.findByProjectUsers_User_Id(id);
    }

    @Override
    public void addUser (Long projectId, String userDesignation) {
        ProjectsEntity project = getProjectById(projectId);
        UsersEntity user = null;
        if (userDesignation.contains("@")) {
            user = usersServiceImpl.getByEmail(userDesignation);
        } else {
            user = usersServiceImpl.getByUsername(userDesignation);
        }
        if (user != null) {
            ProjectUsersEntity projectUser  = new ProjectUsersEntity();
            projectUser .setProject(project);
            projectUser .setUser (user);
            projectUser .setProjectRole("Project User");
            project.getProjectUsers().add(projectUser );
            projectsRepository.save(project);
        }
    }

    @Override
    public void excludeUser (Long projectId, Long userId) {
        ProjectsEntity project = getProjectById(projectId);
        ProjectUsersEntity projectUser  = project.getProjectUsers().stream()
                .filter(pu -> pu.getUser ().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (projectUser  != null) {
            project.getProjectUsers().remove(projectUser );
            projectsRepository.save(project);
        }
    }

    @Override
    public boolean isUserOwner(Long projectId, Long userId) {
        ProjectsEntity project = getProjectById(projectId);
        return project != null && project.getOwner().getId().equals(userId);
    }
}