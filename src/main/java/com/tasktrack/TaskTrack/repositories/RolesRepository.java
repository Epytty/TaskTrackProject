package com.tasktrack.TaskTrack.repositories;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
