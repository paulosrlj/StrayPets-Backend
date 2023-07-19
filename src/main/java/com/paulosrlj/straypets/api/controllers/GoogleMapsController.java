package com.paulosrlj.straypets.api.controllers;

import com.paulosrlj.straypets.api.dto.output.GoogleMapAddressOutput;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.config.modelMapper.LocationDtoConverter;
import com.paulosrlj.straypets.services.GoogleMapsLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/maps")
public class GoogleMapsController {

    @Autowired
    private GoogleMapsLocationService googleMapsLocationService;

    @Autowired
    private LocationDtoConverter locationDtoConverter;

    @GetMapping
    public GoogleMapAddressOutput getFullAddress(BigDecimal latitude, BigDecimal longitude) {
        GoogleMapsAddressResponse address = googleMapsLocationService.getAddressByLatitudeLongitude(latitude, longitude);

        return locationDtoConverter.convertAddressResponseToAddressOutput(address);
    }

}
