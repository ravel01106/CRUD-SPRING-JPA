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

import com.accesodatos.entity.Bank;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.repository.BankRepository;
import com.accesodatos.service.BankService;

/**
 * This class is the controller of the bank table 
 * where all your endpoints are located.
 */

@RestController
@RequestMapping("/api/v1")
public class BankController {

	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	BankService bankService;
	
	/**
	 * This method returns all the banks in the database.
	 * @return a list of banks.
	 */
	
	@GetMapping("/banks")
	public List<Bank> getAllBank(){
		return bankRepository.findAll();
	}
	
	/**
	 * This method returns the database bank specifying 
	 * its id in the endpoint.
	 * @param bankId: id of the specific bank.
	 * @return a specific bank.
	 */
	
	@GetMapping("/banks/{bankId}")
	public Bank getBank(@PathVariable Long bankId) {
		return bankRepository.findById(bankId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"BankId " + bankId + " not found"));	
	}
	
	/**
	 * Displays a list of banks belonging to a specified customer within the endpoint.
	 * @param customerId: Id of the specific customer.
	 * @return a list of banks in a specific customer.
	 */
	
	
	@GetMapping("/customers/{customerId}/banks")
	public List<Bank> getAllBankByCustomerId(@PathVariable Long customerId){
		return bankRepository.findByCustomersCustomerId(customerId);
	}
	
	/**
	 * This method adds a new bank to the database 
	 * by passing the data into the body of the endpoint.
	 * @param bank : the data of the new bank.
	 */
	
	@PostMapping("/banks")
	public void createBank(@RequestBody Bank bank) {
		bankService.saveBank(bank);
	}
	
	/**
	 * This method edits the id bank passed by the endpoint 
	 * where the data is passed by the body of the endpoint.
	 * @param bankId : id of the bank we want to modify.
	 */
	
	@PutMapping("/banks/{bankId}")
	public void updateBank(@PathVariable Long bankId, @RequestBody Bank bank) {
		bankService.updateBank(bank, bankId);
	}
	
	/**
	 * This method deletes the bank from the database by 
	 * passing its id through the endpoint.
	 * @param bankId : id of the bank we want to delete.
	 */
	
	@DeleteMapping("/banks/{bankId}")
	public void deleteBank(@PathVariable Long bankId) {
		bankService.deleteBank(bankId);
	}
	
}
