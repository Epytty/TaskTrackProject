package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
