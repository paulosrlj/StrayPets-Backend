package com.paulosrlj.straypets.api.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleMapAddressOutput {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String full_address;

    private String street;

    @JsonProperty("sub_location")
    private String sublocality;

    private String city;

    private String state;

    private String cep;
}
