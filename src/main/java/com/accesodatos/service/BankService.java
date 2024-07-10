package com.accesodatos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.Bank;
import com.accesodatos.repository.BankRepository;

/**
 * This is the service class for the bank table.
 */

@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;
	
	/**
	 * Method where you store a bank within the repository.
	 * @param bank : Bank we want to create.
	 */
	public void saveBank(Bank bank) {
		Bank _bank = new Bank();
		_bank.setName(bank.getName());
		_bank.setAddress(bank.getAddress());
		bankRepository.save(bank);
	}
	
	/**
	 * This method deletes the bank whose id we pass as parameter.
	 * @param bankId : Id of the bank we want to delete.
	 */
	public void deleteBank(Long bankId) {
		if(bankRepository.existsById(bankId)) {
			bankRepository.deleteById(bankId);
		}
		
	}
	
	/**
	 * This method modifies the bank which we pass its id as a parameter.
	 * with the data of a bank which we also pass as a parameter.
	 * @param bank : Data of the bank we want to modify.
	 * @param bankId : Id of the bank we want to update.
	 */
	public void updateBank(Bank bank, Long bankId) {
		Optional<Bank> optionalBank = bankRepository.findById(bankId);
		if (optionalBank.isPresent()) {
			Bank _bank = optionalBank.get();
			_bank.setName(bank.getName());
			_bank.setAddress(bank.getAddress());
			bankRepository.save(_bank);
		}
	}
	
}
