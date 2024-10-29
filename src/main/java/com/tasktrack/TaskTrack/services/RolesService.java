package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RolesService {

    RolesEntity getRoleById(Long id);
    List<RolesEntity> getAllRoles();
    String getRoleNameById(Long id);
    void saveRole(Long id);
}
