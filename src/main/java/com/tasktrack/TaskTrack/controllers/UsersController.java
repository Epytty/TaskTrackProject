package com.tasktrack.TaskTrack.controllers;

import com.tasktrack.TaskTrack.entities.UsersEntity;
import com.tasktrack.TaskTrack.security.JwtAuthDto;
import com.tasktrack.TaskTrack.security.RefreshTokenDto;
import com.tasktrack.TaskTrack.security.UserCredentialsDto;
import com.tasktrack.TaskTrack.services.impl.RolesServiceImpl;
import com.tasktrack.TaskTrack.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final RolesServiceImpl rolesService;

    @GetMapping("/register")
    public String registerUserPage(Model model) {
        model.addAttribute("user", new UsersEntity());
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthDto> registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            JwtAuthDto jwtAuthDto = usersService.signIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthDto);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed" + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public JwtAuthDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return usersService.refreshToken(refreshTokenDto);
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }

}
