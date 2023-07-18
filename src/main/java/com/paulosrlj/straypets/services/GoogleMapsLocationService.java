package com.paulosrlj.straypets.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class GoogleMapsLocationService {

    @Value("${straypets.google-maps.api-key}")
    private String googleMapsApiKey;

    @Value("${straypets.google-maps.api-url}")
    private String googleMapsUrl;

    private final RestTemplate restTemplate;

    public GoogleMapsLocationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public GoogleMapsAddressResponse getAddressByLatitudeLongitude(BigDecimal latitude, BigDecimal longitude) throws IOException {
        String requestUrl  = String.format(
                "%s?latlng=%s,%s&key=%s",
                googleMapsUrl,
                latitude.toString(), longitude.toString(), googleMapsApiKey);

        var response = this.restTemplate.getForObject(requestUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        var address = objectMapper.readValue(response, GoogleMapsAddressResponse.class);
        address.setLatitude(latitude);
        address.setLongitude(longitude);

        return address;
    }

}
