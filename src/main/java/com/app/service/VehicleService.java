package com.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.interfaces.I_VehicleService;
import com.app.models.Vehicle;
import com.app.repository.VehicleRepository;

/**
 * The Class VehicleService.
 */
@Service
public class VehicleService implements I_VehicleService {
	
	/** The vehicle repository. */
	@Autowired
	private VehicleRepository vehicleRepository;
	
	/** The logger. */
	private Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Vehicle> findAll() {
		LOGGER.info("Fetching all the vehicles");
		return vehicleRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<Vehicle> findById(String id) {
		LOGGER.info("Fetching vehicle with id: {}", id);
		return vehicleRepository.findById(id);
	}

	/**
	 * Find by vehicle reg number.
	 *
	 * @param registrationNumber the registration number
	 * @return the list
	 */
	@Override
	public Vehicle findByVehicleRegNumber(String registrationNumber) {
		LOGGER.info("Fetching vehicle with registration number: {}", registrationNumber);
		List<Vehicle> vehicles = vehicleRepository.findByVehicleRegNumber(registrationNumber);
		LOGGER.info("Vehicles fetched: {}", vehicles.toString());
		return vehicles.get(0);
	}

	/**
	 * Save.
	 *
	 * @param vehicle the vehicle
	 * @return the vehicle
	 */
	@Override
	public Vehicle save(Vehicle vehicle) {
		LOGGER.info("Adding vehicle to database");
		return vehicleRepository.save(vehicle);
	}

	/**
	 * Update.
	 *
	 * @param vehicle the vehicle
	 * @return the vehicle
	 */
	@Override
	public Vehicle update(Vehicle vehicle) {
		LOGGER.info("Updating vehicle to database");
		return vehicleRepository.save(vehicle);
	}
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(String id) {
		LOGGER.info("Deleting vehicle from database");
		vehicleRepository.deleteById(id);
		
	}
	
}
