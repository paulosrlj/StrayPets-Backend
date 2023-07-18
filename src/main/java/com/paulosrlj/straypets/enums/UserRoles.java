package com.paulosrlj.straypets.enums;

import lombok.Data;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private final String role;

    private UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
