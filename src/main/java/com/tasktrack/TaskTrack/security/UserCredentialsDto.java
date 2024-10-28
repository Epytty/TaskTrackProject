package com.tasktrack.TaskTrack.security;

import lombok.Data;

@Data
public class UserCredentialsDto {

    private String username;

    private String password;
}
