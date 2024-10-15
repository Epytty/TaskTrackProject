package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import com.tasktrack.TaskTrack.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public RolesEntity getRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }

    public List<RolesEntity> getAllRoles() {
        return rolesRepository.findAll();
    }

    public String getRoleNameById(Long id) {
        RolesEntity role = rolesRepository.findById(id).orElse(null);
        return role.getName();
    }

    public void saveRole(Long id) {
        RolesEntity role = getRoleById(id);
        rolesRepository.save(role);
    }
}
