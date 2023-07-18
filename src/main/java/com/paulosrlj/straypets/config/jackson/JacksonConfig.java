package com.paulosrlj.straypets.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulosrlj.straypets.config.jackson.converters.InputPetConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JacksonConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    @Autowired
    public JacksonConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new InputPetConverter(objectMapper));
    }
}
