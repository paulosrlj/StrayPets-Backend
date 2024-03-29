package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.api.dto.input.InputPet;
import com.paulosrlj.straypets.api.dto.output.OutputPet;
import com.paulosrlj.straypets.domain.entities.Pet;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class PetDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OutputPet convertToOutput(Pet pet) {
        OutputPet outputPet = modelMapper.map(pet, OutputPet.class);

        return outputPet;
    }

    public List<OutputPet> convertToOutput(List<Pet> pets) {
        List<OutputPet> output = new ArrayList<>();

        for(Pet pet : pets) {
            output.add(modelMapper.map(pet, OutputPet.class));
        }

        return output;
    }

    public Pet convertInputToModelPet(InputPet pet){
        Pet modelPet = modelMapper.map(pet, Pet.class);

        return modelPet;
    }
}
