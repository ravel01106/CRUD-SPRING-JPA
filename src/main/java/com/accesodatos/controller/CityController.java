package com.accesodatos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accesodatos.entity.City;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.repository.CityRepository;
import com.accesodatos.service.CityService;

/**
 * This class is the controller of the city table 
 * where all your endpoints are located.
 */

@RestController
@RequestMapping("/api/v1")
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CityService cityService;
	
	/**
	 * This method returns all the cities in the database.
	 * @return a list of cities.
	 */
	
	@GetMapping("/city")
	public List<City> getAllCity(){
		return cityRepository.findAll();
	}
	
	/**
	 * This method returns the database city specifying 
	 * its id in the endpoint.
	 * @param cityId: id of the specific city.
	 * @return a specific city.
	 */
	
	@GetMapping("/city/{cityId}")
	public City getCity(@PathVariable Long cityId) {
		return cityRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"City " + cityId + " not found"));
	}
	
	/**
	 * This method adds a new city to the database 
	 * by passing the data into the body of the endpoint.
	 * @param city : the data of the new city.
	 */
	
	@PostMapping("/city")
	public void createCity(@RequestBody City city) {
		cityService.saveCity(city);
	}
	
	/**
	 * This method edits the id city passed by the endpoint 
	 * where the data is passed by the body of the endpoint.
	 * @param cityId : id of the city we want to modify.
	 */
	
	@PutMapping("/city/{cityId}")
	public void updateCity(@PathVariable Long cityId, @RequestBody City city) {
		cityService.updateCity(city, cityId);
	}
	
	/**
	 * This method deletes the city from the database by 
	 * passing its id through the endpoint.
	 * @param cityId : id of the city we want to delete.
	 */
	
	@DeleteMapping("/city/{cityId}")
	public void deleteCity(@PathVariable Long cityId) {
		cityService.deleteCity(cityId);
	}
}
