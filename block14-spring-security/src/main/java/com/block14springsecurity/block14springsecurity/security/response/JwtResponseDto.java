package com.block14springsecurity.block14springsecurity.security.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDto {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String role;
    private List<String> roles;

    public JwtResponseDto( String accessToken, Integer id, String username, String role, List<String> roles ) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
        this.roles = roles;
    }

}