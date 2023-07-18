package com.paulosrlj.straypets.api.dto.input;

import com.paulosrlj.straypets.domain.validation.FileTypeValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

    @Data
    public class InputPetWrapper {
    
        @NotNull
        @FileTypeValidation(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
        @Valid
        private ArrayList<MultipartFile> images;
    
        @Valid
        private InputPet data;
    
    }
