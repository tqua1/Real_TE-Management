package com.financialtracker.vault.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private boolean activated;
    private Set<Authority> authorities = new HashSet<>();

    public User() {};
    public User(int userId, String userName, String email, String password, boolean activated) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        if (authorities != null) this.setAuthorities(authorities);
        this.activated = activated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
