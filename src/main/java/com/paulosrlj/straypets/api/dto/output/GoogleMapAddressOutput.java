package com.paulosrlj.straypets.api.dto.output;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoogleMapAddressOutput {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String full_address;

    private String street;

    private String sublocality;

    private String city;

    private String cep;
}
