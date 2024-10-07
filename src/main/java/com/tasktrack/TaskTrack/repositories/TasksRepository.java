package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<TasksEntity, Long> {
    List<TasksEntity> findByProjectId(Long projectId);
}
