package com.paulosrlj.straypets.services;

import com.paulosrlj.straypets.api.dto.input.InputMissingAdoptPet;
import com.paulosrlj.straypets.api.dto.output.GoogleMapAddressOutput;
import com.paulosrlj.straypets.api.dto.web.GoogleMapsAddressResponse;
import com.paulosrlj.straypets.config.modelMapper.GeolocationDtoConverter;
import com.paulosrlj.straypets.config.modelMapper.LocationDTOConverter;
import com.paulosrlj.straypets.config.modelMapper.PetPhotoDTOConverter;
import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.Pet;
import com.paulosrlj.straypets.domain.entities.Photo;
import com.paulosrlj.straypets.domain.entities.User;
import com.paulosrlj.straypets.domain.filters.PetFilter;
import com.paulosrlj.straypets.exception.BusinessException;
import com.paulosrlj.straypets.exception.EntityInUseException;
import com.paulosrlj.straypets.exception.EntityNotFoundException;
import com.paulosrlj.straypets.exception.PersistenseErrorException;
import com.paulosrlj.straypets.interfaces.services.PhotoStorageService;
import com.paulosrlj.straypets.repositories.LocationRepository;
import com.paulosrlj.straypets.repositories.PetPhotoRepository;
import com.paulosrlj.straypets.repositories.PetRepository;
import com.paulosrlj.straypets.repositories.UserRepository;
import com.paulosrlj.straypets.repositories.specs.PetSpecs;
import com.paulosrlj.straypets.services.geolocation.GoogleMapsLocationService;
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
    private UserRepository userRepository;

    @Autowired
    private PetPhotoRepository petPhotoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private PetPhotoDTOConverter petPhotoDTOConverter;

    @Autowired
    private GeolocationDtoConverter geolocationDtoConverter;

    @Autowired
    private GoogleMapsLocationService googleMapsLocationService;

    @Autowired
    private LocationDTOConverter locationDTOConverter;


    //    @Cacheable("findAllPets")
    public List<Pet> findAll(PetFilter petFilter) {
        var pets = petRepository.findAll(PetSpecs.filterPets(petFilter));

        return pets;
    }

    public Pet findPetById(Long id) {
        try {
            Optional<Pet> pet = petRepository.findById(id);
            if (pet.isPresent()) {
                return pet.get();
            }
            throw new EntityNotFoundException(id);

        } catch (Exception ex) {
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

            GoogleMapAddressOutput addressOutput = geolocationDtoConverter.convertAddressResponseToAddressOutput(address);

            Location location = locationDTOConverter.convertAddressOutputToLocation(addressOutput);

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
            persistedPet.setPhotos(persistedPhotos);

            return persistedPet;
        } catch (Exception ex) {
            throw new PersistenseErrorException("Ocorreu um erro ao persistir o pet.", ex);
        }
    }

    @Transactional
    public void adoptPet(InputMissingAdoptPet inputAdoptPet) {
        try {
            Pet pet = findPetById(inputAdoptPet.getPetId());

            if (pet.getMissing())
                throw new BusinessException("Você não pode adotar um animal desaparecido!");

            Optional<User> user = userRepository.findById(inputAdoptPet.getUserId());

            if (user.isEmpty()){
                throw new EntityNotFoundException("O usuário não foi encontrado!");
            }

            pet.adoptPet(user.get());

            petRepository.save(pet);

        } catch (Exception ex) {
            throw new BusinessException("Ocorreu um erro ao adotar o pet.", ex);
        }
    }

    @Transactional
    public void markMissingPetAsFound(InputMissingAdoptPet inputFindMissingPet) {
        try {
            Pet pet = findPetById(inputFindMissingPet.getPetId());

            if (!pet.getMissing()) {
                throw new EntityNotFoundException("Esse pet não está desaparecido!");
            }
            Optional<User> user = userRepository.findById(inputFindMissingPet.getUserId());

            if (user.isEmpty()){
                throw new EntityNotFoundException("O usuário não foi encontrado!");
            }

            pet.markMissingPetAsFound(user.get());

            petRepository.save(pet);

        } catch (Exception ex) {
            throw new BusinessException("Ocorreu um erro ao marcar o pet como encontrado.", ex);
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
