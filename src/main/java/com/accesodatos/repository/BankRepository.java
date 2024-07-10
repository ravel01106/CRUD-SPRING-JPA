package com.accesodatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accesodatos.entity.Bank;

/**
 * This is the bank repository class which inherited from 
 * the JpaRepository and added a search that is associated 
 * with its relationship to the customer table.
 */

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
	List<Bank> findByCustomersCustomerId(Long customerId);
}
