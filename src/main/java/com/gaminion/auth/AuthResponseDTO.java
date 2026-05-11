package com.gaminion.auth;

public class AuthResponseDTO {

    private String token;
    private String username;
    private String email;
    private String message;

    public AuthResponseDTO(String token, String username, String email, String message) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.message = message;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getMessage() { return message; }
}