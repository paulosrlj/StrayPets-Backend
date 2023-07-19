package com.paulosrlj.straypets.services.geolocation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.exception.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

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

    public GoogleMapsAddressResponse getAddressByLatitudeLongitude(BigDecimal latitude, BigDecimal longitude)  {
        try {
            var response = makeRequest(latitude, longitude);

            ObjectMapper objectMapper = new ObjectMapper();

            GoogleMapsAddressResponse address = null;

            address = objectMapper.readValue(response, GoogleMapsAddressResponse.class);
            address.setResults(new ArrayList<>(Collections.singletonList(address.getResults().get(0))));
            address.setLatitude(latitude);
            address.setLongitude(longitude);

            return address;

        } catch (IOException ex) {
            throw new InternalException("Ocorreu um erro ao tentar buscar o endereço", ex);
        }
    }

    public void getFullAddress() {

    }

    private String makeRequest(BigDecimal latitude, BigDecimal longitude) {
        String requestUrl  = String.format(
                "%s?latlng=%s,%s&key=%s",
                googleMapsUrl,
                latitude.toString(), longitude.toString(), googleMapsApiKey);

        return this.restTemplate.getForObject(requestUrl, String.class);
    }

    /*
    * function isMarkerInsideCircle (
    marker: MarkerType,
    circle: CircleType
  ): boolean {
    const R = 6371 // raio médio da Terra em quilômetros
    const lat1 = marker.latitude
    const lon1 = marker.longitude
    const lat2 = circle.center.latitude
    const lon2 = circle.center.longitude

    const dLat = toRad(lat2 - lat1)
    const dLon = toRad(lon2 - lon1)

    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(toRad(lat1)) *
        Math.cos(toRad(lat2)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2)

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    const distance = R * c

    return distance <= circle.radius
  }

  function toRad (value: number): number {
    return (value * Math.PI) / 180
  }
    * */

}
