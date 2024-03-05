package com.financialtracker.vault.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDto {
    private String token;
    private User user;

    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public LoginResponseDto(String jwt, org.springframework.security.core.userdetails.User user) {
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
