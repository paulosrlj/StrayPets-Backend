package com.paulosrlj.straypets.api.dto.auth;

import com.paulosrlj.straypets.enums.UserRoles;
import lombok.Data;

@Data
public class RegisterDTO {

    String email;

    String password;

    UserRoles role;

}
