/**
 * 
 */
package org.ysura.thegarage.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ysura.thegarage.config.Constant;
import org.ysura.thegarage.exception.ErrorResponse;
import org.ysura.thegarage.exception.TheGarageException;
import org.ysura.thegarage.model.Place;
import org.ysura.thegarage.model.Vehicle;
import org.ysura.thegarage.services.PlaceServices;
import org.ysura.thegarage.services.VehicleServices;

/**
 * @author mnaouali
 *
 */
@RestController
@RequestMapping("/rest/vehicle")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	VehicleServices vehicleServices;
	@Autowired
	PlaceServices placeServices;

	/**
	 * Enter a new vehicle
	 * 
	 * @param licenceplate
	 * @param vehicleType
	 * @param placeNumber
	 * @param levelNumber
	 */
	@RequestMapping(value = "/entervehicle", method = RequestMethod.POST)
	public ResponseEntity<Void> enterNewVehicle(@RequestParam("licenceplate") String licenceplate, @RequestParam("placeType") String placeType, @RequestParam("levelNumber") int levelNumber, @RequestParam("placeNumber") int placeNumber
			) throws TheGarageException {
		final StringBuilder sb = new StringBuilder(
				"VehicleController.enterNewVehicle(licenceplate, vehicleType, placeNumber, levelNumber)");
		logger.warn(sb.toString());
		final String message1 = String.format(
				"Trying to enter a new vehicle with a licenceplate: [%s] The [%s] will be add to place number : [%d] in the level number: [%d]",
				licenceplate, placeType, placeNumber, levelNumber);
		logger.info(message1);
		try {
			logger.info("Checking if the vehicle already exist in the garage");
			final Vehicle existingVehicle = vehicleServices.findExistingVehicle(licenceplate);
			if (existingVehicle == null) {
				String vehicleType = null;
				final String message2 = String.format(
						"Vehicle with licence plate [%s] can be entred, checking if the place is valid", licenceplate);
				logger.info(message2);
				if (placeType.equals(Constant.CARSPACE)) {
					vehicleType = Constant.CAR;
					final String message3 = String.format("The vehicle you are trying to enter is a [%s]", vehicleType);
					logger.info(message3);
				}
				if (placeType.equals(Constant.MOTORBIKESPACE)) {
					vehicleType = Constant.MOTORBIKE;
					final String message3 = String.format("The vehicle you are trying to enter is a [%s]", vehicleType);
					logger.info(message3);
				}
				logger.info("Checking if the place is valid");
				final Place place = placeServices.placeExist(placeNumber, levelNumber, placeType);
				if (place != null) {
					final String message4 = String.format(
							"The [%s] in the level [%d] with the place number [%d] is valid", placeType, levelNumber,
							placeNumber);
					logger.info(message4);
					logger.info("Checking if the place is reserved");
					final boolean placeIsReserved = placeServices.placeIsReserved(placeNumber, levelNumber, placeType);
					if (!placeIsReserved) {
						final String message5 = String.format(
								"The [%s] in the level [%d] with the place number [%d] is not reserved, you can enter the car",
								placeType, levelNumber, placeNumber);
						logger.info(message5);
						vehicleServices.enterNewVehicle(licenceplate, vehicleType, placeNumber, levelNumber, placeType);
						final String message6 = String.format(
								"The vehicle with licence plate [%s] entred seccesfully the [%s] in the level [%d] with the place number [%d] ",
								licenceplate, placeType, levelNumber, placeNumber);
						logger.info(message6);
						return new ResponseEntity<Void>(HttpStatus.CREATED);
					}
					final String errorMessage3 = String.format(
							"The [%s] in the level [%d] with the place number [%d] is reserved, you can not enter the car, check another place",
							placeType, levelNumber, placeNumber);
					logger.info(errorMessage3);
					throw new TheGarageException(errorMessage3);
				}
				final String errorMessage2 = String.format(
						"The [%s] in the level [%d] with the place number [%d] is not valid", placeType, levelNumber,
						placeNumber);
				logger.info(errorMessage2);
				throw new TheGarageException(errorMessage2);
			}
			final String errorMessage1 = String.format("The vehicle with licence plate: [%s] is already in the garage",
					licenceplate);
			logger.info(errorMessage1);
			throw new TheGarageException(errorMessage1);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Exit an existing vehicle
	 * 
	 * @param licenceplate
	 */
	@RequestMapping(value = "/exitvehicle", method = RequestMethod.DELETE)
	public ResponseEntity<Void> exitVehicle(@RequestParam("licenceplate") String licenceplate)
			throws TheGarageException {
		final StringBuilder sb = new StringBuilder("VehicleController.exitVehicle(licenceplate)");
		logger.warn(sb.toString());
		final String message1 = String.format("exiting a vehicle with the licence plate: [%s]", licenceplate);
		logger.info(message1);
		try {
			final Vehicle vehicle = vehicleServices.findExistingVehicle(licenceplate);
			if (vehicle == null) {
				final String message2 = String.format("No vehicle found with licence plate: [%s]", licenceplate);
				logger.info(message2 + licenceplate);
				throw new TheGarageException(message2);
			}
			final String message2 = String.format("Vehicle with licence plate [%s] found, trying to exit it",
					licenceplate);
			logger.info(message2);
			vehicleServices.exitExistingVehicle(licenceplate);
			final String message3 = String.format("Vehicle with licence plate [%s] was exited", licenceplate);
			logger.info(message3);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Get the position of an existing vehicle
	 * 
	 * @param licenceplate
	 */
	@RequestMapping(value = "/vehicleplace", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Integer>> getVehiclePlace(@RequestParam("licenceplate") String licenceplate) throws TheGarageException{
		final StringBuilder sb = new StringBuilder("VehicleController.getVehiclePlace(licenceplate)");
		logger.warn(sb.toString());
		logger.info("looking for the place of a vehicle with the licence plate " + licenceplate);
		try {
			final Vehicle vehicle = vehicleServices.findExistingVehicle(licenceplate);
			if (vehicle == null) {
				final String message1 = String.format("No vehicle found with licence plate: [%s]", licenceplate);
				logger.info(message1);
//				return new ResponseEntity<Map<String, Integer>>(HttpStatus.NOT_FOUND);
				throw new TheGarageException(message1);
			}
			final String message2 = String.format("Vehicle with licence plate [%s] found, trying to locate it",
					licenceplate);
			logger.info(message2);
			final Map<String, Integer> vehiclePlace = vehicleServices.findExistingVehiclePlace(licenceplate);
			final String message3 = String.format("Vehicle with licence plate [%s] was located", licenceplate);
			logger.info(message3);
			return new ResponseEntity<Map<String, Integer>>(vehiclePlace, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * should handle only the TheGarageException thrown in any of the layers of
	 * our application.
	 * 
	 * @param Exception
	 */
	@ExceptionHandler(TheGarageException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

}
