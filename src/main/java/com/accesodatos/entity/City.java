package com.accesodatos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class is the template for the city table
 */

@Entity
@Table(name="city_table")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="city_id")
	private long cityId;
	
	@Column(name="name", nullable=false, length=40)
	private String name;
	
	public City() {}
	
	public City(String name) {
		this.name = name;
		
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", name=" + name + "]";
	}
	
	
	
}
