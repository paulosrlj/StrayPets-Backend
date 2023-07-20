package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.api.dto.output.GoogleMapAddressOutput;
import com.paulosrlj.straypets.domain.entities.Address;
import com.paulosrlj.straypets.domain.entities.Location;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Location convertAddressOutputToLocation(GoogleMapAddressOutput address) {
        Location location = new Location();

        location.setLatitude(address.getLatitude());
        location.setLongitude(address.getLongitude());

        Address addressLocation = new Address();
        addressLocation.setSub_location(address.getSublocality());
        addressLocation.setStreet(address.getStreet());
        addressLocation.setCity(address.getCity());
        addressLocation.setCep(address.getCep());
        location.setAddress(addressLocation);

        return location;
    }

}
