package com.accesodatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accesodatos.entity.Customer;

/**
 * This is the customer repository class which inherited 
 * from the JpaRepository and added two lookups that 
 * are associated with its relationship to the bank and 
 * city tables.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByBanksBankId(Long bankId);
	List<Customer> findByCityCityId(Long cityId);
	
}
