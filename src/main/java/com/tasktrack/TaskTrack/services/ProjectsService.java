package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;

import java.util.List;

public interface ProjectsService {
    List<ProjectsEntity> getAllProjects(Long userId);
    ProjectsEntity getProjectById(Long id);
    void createProject(Long userId, String name, String description);
    void saveProject(Long id, String name, String description);
    void saveProjectChanges(ProjectsEntity project);
    void deleteProject(Long id);
    List<ProjectsEntity> getProjectsByUser(Long id);
    boolean isUserOwner(Long projectId, Long userId);
    void addUser (Long projectId, String userDesignation);
    void excludeUser (Long projectId, Long userId);
}