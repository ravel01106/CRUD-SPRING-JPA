package com.accesodatos.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * This class is the template for the customer table
 */

@Entity
@Table(name = "customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private long customerId;
	
	@Column(name="name", nullable=false, length=20)
	private String name;
	
	@Column(name="surname", nullable=false, length=25)
	private String surname;
	
	@Column(name="email", nullable=false, length=50, unique=true)
	private String email;
	
	@Column(name="mobile", nullable=false, length=12)
	private String mobile;
	
	/**
	 * This is the relationship that the customer table has with 
	 * the bank table by creating a new table with two foreing key 
	 * and primary key.
	 */
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="customer_bank_table", 
	joinColumns = @JoinColumn(name="fk_customer_id"),
	inverseJoinColumns = @JoinColumn(name="fk_bank_id"))
	@JsonIgnore
	private Set<Bank> banks = new HashSet<>();
	
	/**
	 * This is the field that is related to the table City, being a foreing key.
	 */
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_city")
	@JsonIgnore
	private City city;
	
	public Customer() {
		//addBank(new Bank("BBVA", "C/ La Herradura, 14"));
	}

	public Customer(String name, String surname, String email, String mobile) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.mobile = mobile;
		//addBank(new Bank("BBVA", "C/ La Herradura, 14"));
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<Bank> getBanks() {
		return banks;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}
	
	/**
	 * These are the helpers we use for the relationship 
	 * between customer and bank
	 */
	
	public void addBank(Bank bank) {
		banks.add(bank);
		bank.getCustomers().add(this);
	}
	
	public void removeBank(Bank bank) {
		banks.remove(bank);
		bank.getCustomers().remove(this);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}
	
	
}
