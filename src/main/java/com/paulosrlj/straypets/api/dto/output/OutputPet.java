package com.paulosrlj.straypets.api.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.Photo;
import com.paulosrlj.straypets.enums.PetGender;
import com.paulosrlj.straypets.enums.PetType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputPet {

    private String name;

    private PetType type;

    private PetGender gender;

    private String breed;

    private Date adoption_date;

    private String comments;

    private Boolean missing;

    private OutputLocation location;

    private List<Photo> photos;
}
