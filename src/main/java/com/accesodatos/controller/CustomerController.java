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

import com.accesodatos.entity.Customer;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.repository.CustomerRepository;
import com.accesodatos.service.CustomerService;

/**
 * This class is the controller of the customer table 
 * where all your endpoints are located.
 */

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	/**
	 * This method returns all the customers in the database.
	 * @return a list of customers.
	 */
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	/**
	 * This method returns the database customer specifying 
	 * its id in the endpoint.
	 * @param customerId: id of the specific customer.
	 * @return a specific customer.
	 */
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"CustomerId " + customerId + " not found"));
	}
	
	/**
	 * Displays a list of customers belonging to a specified bank within the endpoint.
	 * @param bankId: Id of the specific bank.
	 * @return a list of customers in a specific bank.
	 */
	
	@GetMapping("/banks/{bankId}/customers")
	public List<Customer> getAllCustomerByBankId(@PathVariable Long bankId ){
		return customerRepository.findByBanksBankId(bankId);
	}
	
	/**
	 * Displays a list of customers belonging to a specified city within the endpoint
	 * @param cityId: Id of the specific city.
	 * @return a list of customers in a specific city.
	 */
	
	@GetMapping("/city/{cityId}/customers")
	public List<Customer> getAllCustomerByCityId(@PathVariable Long cityId ){
		return customerRepository.findByCityCityId(cityId);
	}
	
	/**
	 * This method adds a new customer to the database 
	 * associated with a specific city by passing the data 
	 * into the body of the endpoint.
	 * @param customer : The data of the new customer.
	 * @param cityId : Id of the specific city.
	 */
	
	@PostMapping("/city/{cityId}/customers")
	public void createCustomerByCityId( @PathVariable Long cityId, @RequestBody Customer customer) {
		customerService.saveCustomer(customer, cityId);
	}
	
	/**
	 * This method edits the id customer passed by the endpoint 
	 * where the data is passed by the body of the endpoint.
	 * @param customerId : id of the customer we want to modify.
	 */
	
	@PutMapping("/customers/{customerId}")
	public void updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
		customerService.updateCustomer(customer, customerId);
		
	}
	
	/**
	 * This method deletes the customer from the database by 
	 * passing its id through the endpoint.
	 * @param customerId : id of the customer we want to delete.
	 */
	
	@DeleteMapping("/customers/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
	}
	
	/**
	 * This method deletes a specific customer from the list of 
	 * a specific bank by passing its ids through the endpoint.
	 * @param bankId: Id of the specific bank.
	 * @param customerId: Id of the specific customer.
	 */
	
	@DeleteMapping("/banks/{bankId}/customers/{customerId}")
	public void deleteCustomerByBankId(@PathVariable("bankId") Long bankId, 
			@PathVariable("customerId") Long customerId) {
		customerService.deleteCustomerByBankId(bankId, customerId);
	}
}
