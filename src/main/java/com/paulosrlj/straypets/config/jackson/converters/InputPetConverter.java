package com.paulosrlj.straypets.config.jackson.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulosrlj.straypets.api.dto.input.InputPet;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;

public class InputPetConverter implements Converter<String, InputPet> {

    private final ObjectMapper objectMapper;

    public InputPetConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public InputPet convert(String source) {
        try {
            return objectMapper.readValue(source, InputPet.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao converter JSON para InputPet.", e);
        }
    }
}
