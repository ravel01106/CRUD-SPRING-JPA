package com.accesodatos.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * This class is the template for the bank table
 */

@Entity
@Table(name = "bank_table")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_id")
	private long bankId;
	
	@Column(name="name", nullable=false, length=20)
	private String name;
	
	@Column(name="address", nullable=false, length=40)
	private String address;
	
	/**
	 * This is the relationship that the bank table has with 
	 * the customer table by creating a new table with two foreing key 
	 * and primary key.
	 */
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "banks")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<Customer> customers = new HashSet<>();
	
	public Bank() {}
	
	public Bank(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public long getBankId() {
		return bankId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * These are the helpers we use for the relationship 
	 * between bank table and customer table
	 */
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
		customer.getBanks().add(this);
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
		customer.getBanks().remove(this);
	}
	
	
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
