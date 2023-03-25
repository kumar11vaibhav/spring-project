package com.app.interfaces;

import java.util.List;
import java.util.Optional;

import com.app.models.Vehicle;

/**
 * The Interface I_VehicleService.
 */
public interface I_VehicleService {
	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Vehicle> findAll ();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Vehicle> findById (String id);
	
	/**
	 * Find by vehicle reg number.
	 *
	 * @param registrationNumber the registration number
	 * @return the optional
	 */
	public Vehicle findByVehicleRegNumber (String registrationNumber);
	
	/**
	 * Save.
	 *
	 * @param vehicle the vehicle
	 * @return the vehicle
	 */
	public Vehicle save (Vehicle vehicle);
	
	/**
	 * Update.
	 *
	 * @param vehicle the vehicle
	 * @return the vehicle
	 */
	public Vehicle update (Vehicle vehicle);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById (String id);
	

}
