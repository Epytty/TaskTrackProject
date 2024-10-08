package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.TasksEntity;
import com.tasktrack.TaskTrack.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    public TasksRepository tasksRepository;

    @Autowired
    public ProjectsService projectsService;

    public List<TasksEntity> getAllTasksByProjectId(Long id) {
        return tasksRepository.findByProjectId(id);
    }

    public void createTask(Long id, String name, String description) {
        ProjectsEntity project = projectsService.getProjectById(id);
        TasksEntity task = new TasksEntity(name, description);
        task.setProject(project);
        tasksRepository.save(task);
    }

    public TasksEntity getTaskById(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    public void saveTask(Long id, String name, String description) {
        TasksEntity task = getTaskById(id);
        task.setName(name);
        task.setDescription(description);
        tasksRepository.save(task);
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }
}