package com.tasktrack.TaskTrack.security;

import lombok.Data;

@Data
public class JwtAuthDto {

    private String accessToken;

    private String refreshToken;
}
