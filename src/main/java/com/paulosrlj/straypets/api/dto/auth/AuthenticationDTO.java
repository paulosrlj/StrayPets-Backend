package com.paulosrlj.straypets.api.dto.auth;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthenticationDTO {

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 50)
    private String password;

}
