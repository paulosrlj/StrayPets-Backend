package com.paulosrlj.straypets.api.dto;

import com.paulosrlj.straypets.enums.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {
}
