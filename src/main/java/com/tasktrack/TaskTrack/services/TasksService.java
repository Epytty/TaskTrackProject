package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import com.tasktrack.TaskTrack.entities.TasksEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TasksService {

    List<TasksEntity> getAllTasksByProjectId(Long id);
    void createTask(Long id, String name, String description);
    TasksEntity getTaskById(Long id);
    void saveTask(Long id, String name, String description);
    void deleteTask(Long id);
}
