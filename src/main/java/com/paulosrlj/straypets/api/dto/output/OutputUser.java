package com.paulosrlj.straypets.api.dto.output;

import com.paulosrlj.straypets.enums.UserRoles;
import lombok.Data;

@Data
public class OutputUser {

    private Long id;

    private String email;

    private UserRoles role;

    private Boolean deactivated;
}
