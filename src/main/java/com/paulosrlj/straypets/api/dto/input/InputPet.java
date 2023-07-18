package com.paulosrlj.straypets.api.dto.input;

import com.paulosrlj.straypets.enums.PetGender;
import com.paulosrlj.straypets.enums.PetType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class InputPet {

    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType type;

    private PetGender gender;

    private String breed;

    private Date adoption_date;

    private String comments;

    private Boolean missing;

    @Valid
    private InputLocation location;

}
