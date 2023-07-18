package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.api.dto.input.InputPet;
import com.paulosrlj.straypets.api.dto.output.OutputPet;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.domain.entities.Address;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.Pet;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class LocationDtoConverter {

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
        address.setLogradouro(googleMapAddress.getResults().get(1).getAddressComponents().get(1).getLongName());
        address.setBairro(googleMapAddress.getResults().get(1).getAddressComponents().get(2).getLongName());
        address.setCidade(googleMapAddress.getResults().get(1).getAddressComponents().get(3).getLongName());
        address.setCep(googleMapAddress.getResults().get(1).getAddressComponents().get(6).getLongName());

        location.setAddress(address);

        location.setLatitude(googleMapAddress.getLatitude());
        location.setLongitude(googleMapAddress.getLongitude());

        return location;
    }

}
