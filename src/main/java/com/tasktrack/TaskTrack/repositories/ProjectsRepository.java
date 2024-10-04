package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long> {
}
