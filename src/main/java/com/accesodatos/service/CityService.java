package com.accesodatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.City;
import com.accesodatos.entity.Customer;
import com.accesodatos.repository.CityRepository;
import com.accesodatos.repository.CustomerRepository;

/**
 * This is the service class for the city table.
 */

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	/**
	 * Method where you store a city within the repository.
	 * @param city : City we want to create.
	 */
	public void saveCity(City city) {
		City _city = new City();
		_city.setName(city.getName());
		
		cityRepository.save(_city);
	}
	
	/**
	 * Method where you store a city within the repository.
	 * Is only used in the class initdatabase.
	 * @param city : City we want to create.
	 */
	public void saveCityApplication(City city) {
		cityRepository.save(city);
	}
	
	/**
	 * This method deletes a city while deleting all.
	 * the customers associated with it.
	 * @param cityId : Id of the city we want to delete.
	 */
	public void deleteCity(Long cityId) {
		if(cityRepository.existsById(cityId)) {
			List<Customer> customers = customerRepository.findByCityCityId(cityId);
			customers.forEach((customer) -> {
				/*
				customer.setCity(null);
				customerRepository.save(customer); */
				customerRepository.delete(customer);
			});
			cityRepository.deleteById(cityId);
		}
	}
	
	/**
	 * This method modifies the city which we pass its id as a parameter.
	 * with the data of a city which we also pass as a parameter.
	 * @param city : Data of the city we want to modify.
	 * @param cityId : Id of the city we want to update.
	 */
	public void updateCity(City city, Long cityId) {
		Optional<City> optionalCity = cityRepository.findById(cityId);
		if (optionalCity.isPresent()) {
			City _city = optionalCity.get();
			_city.setName(city.getName());
			cityRepository.save(_city);
		}
		
	}
}
