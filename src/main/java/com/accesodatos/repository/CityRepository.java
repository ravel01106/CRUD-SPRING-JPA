package com.accesodatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accesodatos.entity.City;

/**
 * This is the city repository class which is inherited from the JpaRepository.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
