package com.tasktrack.TaskTrack.services.impl;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.TasksEntity;
import com.tasktrack.TaskTrack.repositories.TasksRepository;
import com.tasktrack.TaskTrack.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;
    private final ProjectsServiceImpl projectsServiceImpl;

    @Override
    public List<TasksEntity> getAllTasksByProjectId(Long id) {
        return tasksRepository.findByProjectId(id);
    }

    @Override
    public void createTask(Long id, String name, String description) {
        ProjectsEntity project = projectsServiceImpl.getProjectById(id);
        TasksEntity task = new TasksEntity(name, description);
        task.setProject(project);
        tasksRepository.save(task);
    }

    @Override
    public TasksEntity getTaskById(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTask(Long id, String name, String description) {
        TasksEntity task = getTaskById(id);
        task.setName(name);
        task.setDescription(description);
        tasksRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }
}