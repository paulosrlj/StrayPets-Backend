package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.api.dto.output.GoogleMapAddressOutput;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.domain.entities.Address;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.enums.RegionsType;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class GeolocationDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Location convertAddressToLocation(GoogleMapsAddressResponse googleMapAddress) {
        Location location = new Location();

        Address address = new Address();
        /*
         * Índice 0 -> Número da rua
         * Índice 1 -> Nome da rua
         * Índice 2 -> Bairro
         * Índice 3 -> Cidade
         * Índice 4 -> Estado
         * Índice 5 -> País
         * Índice 6 -> Cep
         * */
        address.setStreet(googleMapAddress.getResults().get(1).getAddressComponents().get(1).getLongName());
        address.setSub_location(googleMapAddress.getResults().get(1).getAddressComponents().get(2).getLongName());
        address.setCity(googleMapAddress.getResults().get(1).getAddressComponents().get(3).getLongName());
        address.setCep(googleMapAddress.getResults().get(1).getAddressComponents().get(6).getLongName());

        location.setAddress(address);

        location.setLatitude(googleMapAddress.getLatitude());
        location.setLongitude(googleMapAddress.getLongitude());

        return location;
    }

    public GoogleMapAddressOutput convertAddressResponseToAddressOutput(GoogleMapsAddressResponse googleMapAddress) {
        GoogleMapAddressOutput output = new GoogleMapAddressOutput();
        var result = googleMapAddress.getResults().get(0);

        output.setFull_address(result.getFormattedAddress());

        output.setLatitude(googleMapAddress.getLatitude());
        output.setLongitude(googleMapAddress.getLongitude());

        output.setLatitude(result.getGeometry().getLocation().getLat());
        output.setLongitude(result.getGeometry().getLocation().getLng());

        for (var addressComp : result.getAddressComponents()) {
            setAddressComponentToCorrectField(output, addressComp);
        }

        return output;
    }

    private void setAddressComponentToCorrectField(
            GoogleMapAddressOutput addressList,
            GoogleMapsAddressResponse.AddressComponent addressComponent
    ) {
        if (addressComponent.getTypes().contains(RegionsType.STREET.getGoogleMapsKey())) {
            addressList.setStreet(addressComponent.getLongName());
        } else if (addressComponent.getTypes().contains(RegionsType.SUBLOCALITY.getGoogleMapsKey())) {
            addressList.setSublocality(addressComponent.getLongName());
        } else if (addressComponent.getTypes().contains(RegionsType.CITY.getGoogleMapsKey())) {
            addressList.setCity(addressComponent.getLongName());
        } else if (addressComponent.getTypes().contains(RegionsType.POSTAL_CODE.getGoogleMapsKey())) {
            addressList.setCep(addressComponent.getLongName());
        } else if (addressComponent.getTypes().contains(RegionsType.STATE.getGoogleMapsKey())) {
            addressList.setState(addressComponent.getShortName());
        }
    }
}
