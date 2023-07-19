package com.paulosrlj.straypets.api.dto.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GoogleMapsAddressResponse {

    @JsonProperty("results")
    private List<AddressResult> results;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class AddressResult {
        @JsonProperty("formatted_address")
        private String formattedAddress;

        @JsonProperty("address_components")
        private List<AddressComponent> addressComponents;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class AddressComponent {
        @JsonProperty("long_name")
        private String longName;

        @JsonProperty("short_name")
        private String shortName;

        @JsonProperty("types")
        private List<String> types;

    }
}

