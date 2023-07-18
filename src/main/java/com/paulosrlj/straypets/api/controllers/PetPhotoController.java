package com.paulosrlj.straypets.api.controllers;

import com.paulosrlj.straypets.api.dto.input.InputPetWrapper;
import com.paulosrlj.straypets.services.S3PhotoPhotoStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pet-photo")
public class PetPhotoController {




    @Autowired
    private S3PhotoPhotoStorageService s3PhotoStorageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createPetPhoto(@Valid @ModelAttribute InputPetWrapper petPhotos) throws IOException {


        System.out.println(petPhotos.getImages().get(0).getOriginalFilename());
        System.out.println(petPhotos.getImages().get(1).getOriginalFilename());

//        System.out.println(petphoto.getFile().get(0).getOriginalFilename());
//        MultipartFile file = petphoto.getFile().get(0);
//        MultipartFile file2 = petphoto.getFile().get(1);
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file2.getOriginalFilename());

//        s3PhotoStorageService.store(petphoto);

    }



}
