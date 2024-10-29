package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.security.JwtAuthDto;
import com.tasktrack.TaskTrack.security.RefreshTokenDto;
import com.tasktrack.TaskTrack.security.UserCredentialsDto;
import org.apache.tomcat.websocket.AuthenticationException;

import java.util.List;

public interface UsersService {

    JwtAuthDto signIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException;

    JwtAuthDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception;
    void registerUser(String username, String email, String password);
    List<UsersEntity> getAllUsers();
    UsersEntity getUserById(Long id);
    void saveUserRole(Long id, String role);
    UsersEntity getByUsername(String username);
    UsersEntity getByEmail(String email);
}
