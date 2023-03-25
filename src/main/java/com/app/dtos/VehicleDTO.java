package com.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
	
	/** The id. */
	private String id;

	/** The make. */
	private String make;

	/** The model. */
	private String model;

	/** The category. */
	private String category;

	/** The vehicle reg number. */
	private String vehicleRegNumber;

}
