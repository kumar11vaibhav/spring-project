package com.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Vehicle.
 */
@Entity
@Table(name = "vehicles")

/**
 * Instantiates a new vehicle without arguments.
 */
@NoArgsConstructor

/**
 * Instantiates a new vehicle.
 *
 * @param id the id
 * @param make the make
 * @param model the model
 * @param category the category
 * @param vehicleRegNumber the vehicle reg number
 */
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {

	/** The id. */
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private String id;
	
	/** The make. */
	private String make;
	
	/** The model. */
	private String model;
	
	/** The category. */
	private String category;
	
	/** The vehicle reg number. */
	@Column(name = "vehicle_reg_no")
	private String vehicleRegNumber;
	
	/**
	 * Gets the vehicle reg number.
	 *
	 * @return the vehicle reg number
	 */
	@JsonProperty("vehicleRegistrationNumber")
	public String getVehicleRegNumber() {
		return this.vehicleRegNumber;
	}
	
	/**
	 * Sets the vehicle reg number.
	 *
	 * @param vehicleRegNumber the new vehicle reg number
	 */
	@JsonProperty("vehicleRegistrationNumber")
	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		
		return "ID: " + this.id + " Make: " + this.make + " Model: " + this.model 
				+ " Category: " + this.category + " Vehicle Registration Number: " + this.vehicleRegNumber;
	} 

}
