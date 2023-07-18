package com.paulosrlj.straypets.api.dto.output;

import com.paulosrlj.straypets.domain.entities.Address;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.Photo;
import com.paulosrlj.straypets.enums.PetGender;
import com.paulosrlj.straypets.enums.PetType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OutputLocation {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Address address;
}
