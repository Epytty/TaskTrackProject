package com.tasktrack.TaskTrack.services.impl;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.repositories.UsersRepository;
import com.tasktrack.TaskTrack.security.JwtAuthDto;
import com.tasktrack.TaskTrack.security.RefreshTokenDto;
import com.tasktrack.TaskTrack.security.UserCredentialsDto;
import com.tasktrack.TaskTrack.security.jwt.JwtService;
import com.tasktrack.TaskTrack.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RolesServiceImpl rolesService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthDto signIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        UsersEntity user = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(user.getUsername());
    }

    @Override
    public JwtAuthDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            UsersEntity user = getByUsername(jwtService.getUsernameFromToken(refreshToken));
            return jwtService.refreshBaseToken(user.getEmail(), refreshToken);
        }
        throw new  AuthenticationException("Invalid refresh token");
    }

    @Override
    public void registerUser(String username, String email, String password) {
        UsersEntity user = new UsersEntity();
        String role = rolesService.getRoleNameById(1L);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        usersRepository.save(user);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUserRole(Long id, String role) {
        UsersEntity user = getUserById(id);
        user.setRole(role);
        usersRepository.save(user);
    }

    @Override
    public UsersEntity getByUsername(String username) {
        return usersRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UsersEntity getByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    private UsersEntity findByCredentials(UserCredentialsDto userCredentialsDto) throws AuthenticationException{
        Optional<UsersEntity> optionalUser = usersRepository.findByUsername(userCredentialsDto.getUsername());
        if (optionalUser.isPresent()) {
            UsersEntity user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Username or password is not correct");
    }

}
