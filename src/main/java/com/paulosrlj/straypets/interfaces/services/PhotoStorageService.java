package com.paulosrlj.straypets.interfaces.services;

import com.paulosrlj.straypets.api.dto.input.InputPetWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoStorageService {

    String store(MultipartFile photo);
}
