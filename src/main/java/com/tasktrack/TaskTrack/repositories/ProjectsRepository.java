package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long> {
    List<ProjectsEntity> findByProjectUsers_User_Id(Long userId);
}