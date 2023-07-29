package com.paulosrlj.straypets.api.dto.output;

import com.paulosrlj.straypets.enums.PetType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OutputPetLocationDTO {

    private PetType type;

    private BigDecimal latitude;

    private BigDecimal longitude;

}
