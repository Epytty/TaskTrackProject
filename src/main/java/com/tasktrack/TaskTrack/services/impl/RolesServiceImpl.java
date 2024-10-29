package com.tasktrack.TaskTrack.services.impl;

import com.tasktrack.TaskTrack.entities.RolesEntity;
import com.tasktrack.TaskTrack.repositories.RolesRepository;
import com.tasktrack.TaskTrack.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public RolesEntity getRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }

    @Override
    public List<RolesEntity> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public String getRoleNameById(Long id) {
        RolesEntity role = rolesRepository.findById(id).orElse(null);
        return role.getName();
    }

    @Override
    public void saveRole(Long id) {
        RolesEntity role = getRoleById(id);
        rolesRepository.save(role);
    }
}
