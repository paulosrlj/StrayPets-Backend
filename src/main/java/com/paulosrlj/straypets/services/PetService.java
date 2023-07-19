package com.paulosrlj.straypets.services;

import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.config.modelMapper.LocationDtoConverter;
import com.paulosrlj.straypets.config.modelMapper.PetPhotoDTOConverter;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.Pet;
import com.paulosrlj.straypets.domain.entities.Photo;
import com.paulosrlj.straypets.domain.filters.PetFilter;
import com.paulosrlj.straypets.exception.EntityInUseException;
import com.paulosrlj.straypets.exception.EntityNotFoundException;
import com.paulosrlj.straypets.exception.PersistenseErrorException;
import com.paulosrlj.straypets.interfaces.services.PhotoStorageService;
import com.paulosrlj.straypets.repositories.LocationRepository;
import com.paulosrlj.straypets.repositories.PetPhotoRepository;
import com.paulosrlj.straypets.repositories.PetRepository;
import com.paulosrlj.straypets.repositories.specs.PetSpecs;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetPhotoRepository petPhotoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private PetPhotoDTOConverter petPhotoDTOConverter;

    @Autowired
    private LocationDtoConverter locationDtoConverter;

    @Autowired
    private GoogleMapsLocationService googleMapsLocationService;

    public List<Pet> findAll(PetFilter petFilter) {
        return petRepository.findAll(PetSpecs.filterPets(petFilter));
    }

    public Pet findPetById(Long id) {
        try {
            Optional<Pet> pet = petRepository.findById(id);
            if (pet.isPresent()){
                return pet.get();
            }
            throw new EntityNotFoundException(id);

        } catch (Exception ex){
            throw new EntityNotFoundException("Não encontrado");
        }
    }

    @Transactional
    public Pet createPet(Pet pet, List<MultipartFile> petPhotos) {
        try {
            // Location
            GoogleMapsAddressResponse address = googleMapsLocationService.getAddressByLatitudeLongitude(
                    pet.getLocation().getLatitude(),
                    pet.getLocation().getLongitude());

            Location location = locationDtoConverter.convertAddressToLocation(address);
            var persistedLocation = locationRepository.save(location);

            pet.setLocation(persistedLocation);

            var persistedPet = petRepository.save(pet);
            petRepository.flush();

            // Pets
            List<Photo> photos = new ArrayList<>();

            for (MultipartFile photo : petPhotos) {
                var photoUri = photoStorageService.store(photo);
                var entityPhoto = petPhotoDTOConverter.convertMultiPartToPhoto(photo, photoUri);
                entityPhoto.setPet(persistedPet);
                photos.add(entityPhoto);
            }

            var persistedPhotos = petPhotoRepository.saveAll(photos);
//            persistedPet.setPhotos(persistedPhotos);

            return persistedPet;
        } catch (Exception ex) {
            throw new PersistenseErrorException("Ocorreu um erro ao persistir o pet.", ex);
        }
    }

    @Transactional
    public void delete(Long petId) {
        try {
            petRepository.deleteById(petId);
            petRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(petId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    "O pet não pode ser removido pois está sendo referenciado por outra entidade");
        }
    }

}
