package com.paulosrlj.straypets.domain.filters;

import com.paulosrlj.straypets.enums.PetGender;
import com.paulosrlj.straypets.enums.PetType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode
public class PetFilter {

    private String name;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Enumerated(EnumType.STRING)
    private PetGender gender;

    private String breed;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_adoption_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_adoption_date;

    private String comments;

    private Boolean missing;

    private String cep;

    private String sub_locality;

}
