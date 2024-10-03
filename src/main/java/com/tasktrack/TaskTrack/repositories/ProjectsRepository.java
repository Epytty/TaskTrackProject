package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Project, Long> {
}
