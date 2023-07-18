package com.paulosrlj.straypets.repositories;

import com.paulosrlj.straypets.domain.entities.Photo;
import com.paulosrlj.straypets.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PetPhotoRepository extends JpaRepository<Photo, Long> {

}
