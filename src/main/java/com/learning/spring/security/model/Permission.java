package com.learning.spring.security.model;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    USERS_CHECKER("users:check");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
