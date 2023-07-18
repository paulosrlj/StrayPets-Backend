package com.paulosrlj.straypets.config.modelMapper;

import com.paulosrlj.straypets.domain.entities.Photo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Component
public class PetPhotoDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public List<Photo> convertMultiPartListToPhotoList(List<MultipartFile> photos) {
        List<Photo> petPhotos = new ArrayList<>();

        for (MultipartFile photo : photos) {
            Photo entityPhoto = modelMapper.map(photo, Photo.class);
            entityPhoto.setPhoto_name(LocalDateTime.now() + photo.getOriginalFilename());
            petPhotos.add(entityPhoto);
        }

        return petPhotos;
    }

    public Photo convertMultiPartToPhoto(MultipartFile photo, String photoUri) {
        Photo entityPhoto = new Photo();
        entityPhoto.setPhoto_name(LocalDateTime.now() + photo.getOriginalFilename());
        entityPhoto.setPhoto_uri(photoUri);

        return entityPhoto;
    }
}
