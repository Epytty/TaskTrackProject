package com.tasktrack.TaskTrack.repositorys;

import com.tasktrack.TaskTrack.models.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long> {
}
