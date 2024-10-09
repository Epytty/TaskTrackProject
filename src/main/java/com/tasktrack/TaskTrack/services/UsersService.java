package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> user = usersRepository.findByUsername(username);
        return user.map(UsersDetailsService::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public void registerUser(String username, String password, String roles) {
        UsersEntity user = new UsersEntity(username, password, roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
}
