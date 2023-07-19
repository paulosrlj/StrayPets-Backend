package com.paulosrlj.straypets.enums;

import lombok.Data;


public enum RegionsType {

    STREET("route"),
    CITY("administrative_area_level_2"),
    SUBLOCALITY("sublocality"),
    STATE("administrative_area_level_1"),
    COUNTRY("country"),
    POSTAL_CODE("postal_code");

    private final String googleMapsKey;

    RegionsType(String googleMapsKey) {
        this.googleMapsKey = googleMapsKey;
    }

    public String getGoogleMapsKey() {
        return this.googleMapsKey;
    }

}
