package com.accesodatos.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.Bank;
import com.accesodatos.entity.City;
import com.accesodatos.entity.Customer;
import com.accesodatos.repository.BankRepository;
import com.accesodatos.repository.CityRepository;
import com.accesodatos.repository.CustomerRepository;

/**
 * This is the service class for the customer table.
 */

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	/**
	 * This method creates all the customers in the list. 
	 * that we pass by parameter.
	 * @param customers : List of customers we want to create.
	 */
	public void saveCityCustomerList(List<Customer> customers) {
		customerRepository.saveAll(customers);
	}
	
	/**
	 * Method where a customer is stored within a specified city.
	 * @param customer : Customer we want to create.
	 * @param cityId : Id of the specific city.
	 */
	public void saveCustomer(Customer customer, Long cityId) {
		Optional<City> optionalCity = cityRepository.findById(cityId);
		if (optionalCity.isPresent()) {
			Customer _customer = new Customer();
			
			_customer.setName(customer.getName());
			_customer.setSurname(customer.getSurname());
			_customer.setEmail(customer.getEmail());
			_customer.setMobile(customer.getMobile());
			_customer.setCity(optionalCity.get());
			
			customerRepository.save(_customer);
		}	
	}
	
	/**
	 * This method deletes the customer whose id we pass as parameter.
	 * @param customerId : Id of the customer we want to delete.
	 */
	public void deleteCustomer(Long customerId) {
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);			
		}
	}
	
	/**
	 * This method modifies the customer which we pass its id as a parameter 
	 * with the data of a customer which we also pass as a parameter.
	 * @param customer : Data of the customer we want to modify.
	 * @param customerId : Id of the customer we want to update.
	 */
	public void updateCustomer(Customer customer, Long customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isPresent()) {
			Customer _customer = optionalCustomer.get();
			_customer.setName(customer.getName());
			_customer.setSurname(customer.getSurname());
			_customer.setEmail(customer.getEmail());
			_customer.setMobile(customer.getMobile());
			
			customerRepository.save(_customer);
		}
	}
	
	/**
	 * This method deletes from a specific bank a specific customer 
	 * by passing the two ids by parameter.
	 * @param customerId : id of the customer we want to remove from the bank.
	 * @param bankId : id of the bank we want to remove the customer.
	 */
	public void deleteCustomerByBankId(Long bankId, Long customerId) {
		Optional<Bank> optionalBank = bankRepository.findById(bankId);
		Bank _bank = optionalBank.get();
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		Customer _customer = optionalCustomer.get();
		Set<Customer> customers = _bank.getCustomers();
		
		if (optionalBank.isPresent() && optionalCustomer.isPresent()  && customers.contains(_customer)) {
			_bank.removeCustomer(_customer);
			customerRepository.save(_customer);
			bankRepository.save(_bank);
		}
	}
	
	
	
}
