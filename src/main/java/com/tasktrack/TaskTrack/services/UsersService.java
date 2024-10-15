package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.TasksEntity;
import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> user = usersRepository.findByUsername(username);
        return user.map(UsersDetailsService::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void registerUser(String username, String password) {
        UsersEntity user = new UsersEntity();
        String role = rolesService.getRoleNameById(1L);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        usersRepository.save(user);
    }

    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public void saveUserRole(Long id, String role) {
        UsersEntity user = getUserById(id);
        user.setRole(role);
        usersRepository.save(user);
    }

    public UsersEntity findByUsername(String username) {
        return usersRepository.findByUsername(username).orElse(null);
    }
}
