package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.constants.VehiclesControllerConstants;
import com.app.dtos.VehicleDTO;
import com.app.models.Vehicle;
import com.app.service.VehicleService;

/**
 * The Class VehiclesController.
 */
@RestController
@RequestMapping(path = VehiclesControllerConstants.VEHICLES_API)
public class VehiclesController implements VehiclesControllerConstants {
	
	/** The vehicle repository. */
	@Autowired
	private VehicleService vehicleService;
	
	/** The vehicle DTO. */
	@Autowired
	private ModelMapper modelMapper;
	
	/** The logger. */
	private Logger LOGGER = LoggerFactory.getLogger(VehiclesController.class);
	
	/**
	 * Gets the all vehicles.
	 *
	 * @return the all vehicles
	 */
	@GetMapping (path = GET_VEHICLES_API)
	public ResponseEntity<List<VehicleDTO>> getAllVehicles () {
		LOGGER.info("Fetching all the vehicles");
		
		try {
			
			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles = vehicleService.findAll();
			
			if (vehicles.isEmpty()) {
				
				LOGGER.info("No vehicle found in the database!");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			LOGGER.info("Vehicles fetched from the database successfully.");
			List<VehicleDTO> vehiclesDTO = vehicles.stream()
											.map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
											.collect(Collectors.toList()); 
			return new ResponseEntity<>(vehiclesDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while fetching vehicles from the database due to: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets the vehicle by id.
	 *
	 * @param id the id
	 * @return the vehicle by id
	 */
	@GetMapping (path = GET_VEHICLES_BY_ID_NO_API)
	public ResponseEntity<VehicleDTO> getVehicleById (@PathVariable (value = "id") String id) {
		LOGGER.info("Fetching vehicles by id");
		
		try {
			
			Optional<Vehicle> vehicle =  vehicleService.findById(id);
			if (vehicle.isPresent()) {
				
				LOGGER.info("Vehicle fetched from the database successfully with the id: {}", id);
				VehicleDTO vehicleDTO = modelMapper.map(vehicle.get(), VehicleDTO.class);
				return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
			}
			
			LOGGER.info("No vehicle found in the database with the id: {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while fetching vehicle from the database due to: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets the vehicle by registration number.
	 *
	 * @param registrationNumber the registration number
	 * @return the vehicle by registration number
	 */
	@GetMapping (path = GET_VEHICLES_BY_REG_NO_API)
	public ResponseEntity<VehicleDTO> getVehicleByRegNumber (@RequestParam (value = "regNumber") String registrationNumber) {
		LOGGER.info("Fetching vehicles by registration number");
		
		try {
			Vehicle vehicle =  vehicleService.findByVehicleRegNumber(registrationNumber);
			if (Objects.isNull(vehicle)) {
				
				LOGGER.info("No vehicle found in the database with the registration number: {}", registrationNumber);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			LOGGER.info("Vehicle fetched from the database successfully with the registration number: {}", registrationNumber);
			VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
			return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while fetching vehicle from the database due to: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Adds the vehicle.
	 *
	 * @param vehicle the vehicle
	 * @return the response entity
	 */
	@PostMapping (path = ADD_VEHICLE_API)
	public ResponseEntity<VehicleDTO> addVehicle (@RequestBody Vehicle vehicle) {
		LOGGER.info("Adding vehicle to the database.");
		
		try {
			Vehicle savedVehicle = null;
			if (Objects.nonNull(vehicle)) {
				
				LOGGER.info("Calling save operation on the database");
				savedVehicle = vehicleService.save(vehicle);
			}
			
			if (Objects.nonNull(savedVehicle)) {
				
				LOGGER.info("Vehicle successfully added to the database.");
				VehicleDTO savedVehicleDTO = modelMapper.map(savedVehicle, VehicleDTO.class);
				return new ResponseEntity<>(savedVehicleDTO, HttpStatus.CREATED);
			}
			
			LOGGER.info("Vehicle could not be added to the database. Please try again!");
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while adding vehicle to the database due to: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update vehicle by registration number.
	 */
	@PutMapping (path = UPDATE_VEHICLE_API)
	public ResponseEntity<VehicleDTO> updateVehicleByRegNumber (@RequestBody Vehicle vehicle) {
		LOGGER.info("Updating vehicle in the database.");
		
		if (vehicle.getId().isEmpty()) {
			LOGGER.error("Vehicle id cannot be null.");
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
		Optional<Vehicle> fetchedVehicleData = vehicleService.findById(vehicle.getId());
		try {
			if (fetchedVehicleData.isPresent()) {
				
				LOGGER.info("Vehicle with the id {} found in the database.", vehicle.getId());
				Vehicle updatedVehicle = new Vehicle(fetchedVehicleData.get().getId(), 
						vehicle.getMake(), vehicle.getModel(), vehicle.getCategory(), vehicle.getVehicleRegNumber());
				
				LOGGER.info("Calling update operation on the database");
				updatedVehicle = vehicleService.save(updatedVehicle);
				
				LOGGER.info("Vehicle with the id {} updated in the database successfully.", vehicle.getId());
				VehicleDTO updatedVehicleDTO = modelMapper.map(updatedVehicle, VehicleDTO.class);
				return new ResponseEntity<>(updatedVehicleDTO, HttpStatus.OK);

			}
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while updating vehicle to the database due to: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("Vehicle with the id {} not found in the database.", vehicle.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Delete vehicle.
	 */
	@DeleteMapping (path = DELETE_VEHICLE_API)
	public ResponseEntity<HttpStatus> deleteVehicle (@PathVariable (value = "id") String id) {
		LOGGER.info("Deleting vehicle in the database.");
		
		try {
			
			vehicleService.deleteById(id);
			LOGGER.info("Vehicle with id {} deleted from the database successfully.", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while deleting vehicle to the database due to: {}", e.getMessage());
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
