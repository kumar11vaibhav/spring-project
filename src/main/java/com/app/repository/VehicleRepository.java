package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Vehicle;

/**
 * The Interface VehicleRepository.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	
	
	/**
	 * Find by vehicle reg number.
	 *
	 * @param vehicleRegNumber the vehicle reg number
	 * @return the list
	 */
	public List<Vehicle> findByVehicleRegNumber(String vehicleRegNumber);
	
	/**
	 * Find by category.
	 *
	 * @param category the category
	 * @return the list
	 */
	public List<Vehicle> findByCategory(String category);
	
	/**
	 * Find by make.
	 *
	 * @param make the make
	 * @return the list
	 */
	public List<Vehicle> findByMake(String make);
	
	/**
	 * Find by model.
	 *
	 * @param model the model
	 * @return the list
	 */
	public List<Vehicle> findByModel(String model); 

}
