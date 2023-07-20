package com.paulosrlj.straypets.api.dto.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paulosrlj.straypets.domain.entities.Location;
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

        private GeometryType geometry;
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

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeometryType {
        private LocationType location;
    }

    @Data
    public static class LocationType {
        private BigDecimal lat;
        private BigDecimal lng;
    }
}

