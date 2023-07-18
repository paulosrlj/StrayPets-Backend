package com.paulosrlj.straypets.api.dto.input;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Data
public class InputLocation {

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

}
