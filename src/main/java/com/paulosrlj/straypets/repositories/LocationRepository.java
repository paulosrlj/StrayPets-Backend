package com.paulosrlj.straypets.repositories;

import com.paulosrlj.straypets.domain.entities.Location;
import com.paulosrlj.straypets.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
