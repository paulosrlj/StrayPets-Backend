package com.paulosrlj.straypets.api.controllers;

import com.paulosrlj.straypets.api.dto.input.InputPetWrapper;
import com.paulosrlj.straypets.api.dto.output.OutputPet;
import com.paulosrlj.straypets.config.modelMapper.PetDtoConverter;
import com.paulosrlj.straypets.domain.entities.Pet;
import com.paulosrlj.straypets.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetDtoConverter petDtoConverter;


    @GetMapping("/{id}")
    public OutputPet getPetById(@PathVariable Long id) throws Exception {
        Pet pet = petService.findPetById(id);

        return petDtoConverter.convertToOutput(pet);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public OutputPet create(@Valid @ModelAttribute InputPetWrapper data) {
        Pet pet = petDtoConverter.convertInputToModelPet(data.getData());

        return petDtoConverter.convertToOutput(petService.createPet(pet, data.getImages()));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) {
        petService.delete(id);
    }
}
