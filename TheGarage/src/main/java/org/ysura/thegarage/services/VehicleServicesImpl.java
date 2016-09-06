/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ysura.thegarage.config.Constant;
import org.ysura.thegarage.model.Car;
import org.ysura.thegarage.model.Level;
import org.ysura.thegarage.model.Motorbike;
import org.ysura.thegarage.model.Place;
import org.ysura.thegarage.model.Vehicle;
import org.ysura.thegarage.repositories.LevelRepository;
import org.ysura.thegarage.repositories.PlaceRepository;
import org.ysura.thegarage.repositories.VehicleRepositoy;

/**
 * @author mnaouali
 *
 */
@Service
public class VehicleServicesImpl implements VehicleServices {

	private static final Logger logger = LoggerFactory.getLogger(VehicleServicesImpl.class);

	@Autowired
	VehicleRepositoy vehicleRepositoy;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	LevelRepository levelRepository;
	@Autowired
	PlaceServices placeServices;

	/**
	 * Enter a new vehicle
	 * 
	 * @param licenceplate
	 * @param vehicleType
	 * @param placeNumber
	 * @param levelNumber
	 * @param placeType
	 */
	public void enterNewVehicle(String licenceplate, String vehicleType, int placeNumber, int levelNumber,
			String placeType) {
		final StringBuilder sb = new StringBuilder(
				"VehicleServicesImpl.enterNewVehicle(licenceplate, vehicleType, placeNumber, levelNumber, placeType)");
		logger.warn(sb.toString());
		final String message1 = String.format(
				"Trying to enter a new vehicle with a licenceplate: [%s]. The [%s] will be add to [%s] number : [%d] in the level: [%d]",
				licenceplate, vehicleType, placeType, placeNumber, levelNumber);
		logger.info(message1);
		final Level level = levelRepository.findByLevelNumber(levelNumber);
		final Place place = placeRepository.findByPlaceNumberAndLevelAndType(placeNumber, level, placeType);
		if (vehicleType.equals(Constant.CAR) && placeType.equals(Constant.CARSPACE)) {
			logger.info("Trying to enter a car");
			place.setReserved(true);
			placeRepository.save(place);
			vehicleRepositoy.save(new Car(licenceplate, place));
		} else if (vehicleType.equals(Constant.MOTORBIKE)  && placeType.equals(Constant.MOTORBIKESPACE)) {
			logger.info("Trying to enter a motorbike");
			place.setReserved(true);
			placeRepository.save(place);
			vehicleRepositoy.save(new Motorbike(licenceplate, place));
		}
	}

	/**
	 * Search for an existing vehicle
	 * 
	 * @param licenceplate
	 */
	public Vehicle findExistingVehicle(String licenceplate) {
		final StringBuilder sb = new StringBuilder("VehicleServicesImpl.findExistingVehicle(licenceplate)");
		logger.warn(sb.toString());
		final String message1 = String.format("Looking for a vehicle with the licenceplate: [%s]", licenceplate);
		logger.info(message1);
		Vehicle vehicle = null;
		try {
			vehicle = vehicleRepositoy.findByLicenceplate(licenceplate);
		} catch (NullPointerException e) {
			final String message2 = String.format("[%s] not found", licenceplate);
			logger.error(message2);
			System.err.println(licenceplate + message2);
		}
		return vehicle;
	}

	/**
	 * Get the position of an existing vehicle
	 * 
	 * @param licenceplate
	 */
	public Map<String, Integer> findExistingVehiclePlace(String licenceplate) {
		final StringBuilder sb = new StringBuilder("VehicleServicesImpl.findExistingVehiclePlace(licenceplate)");
		logger.warn(sb.toString());
		final String message1 = String.format("Looking for the position of the vehicle with the licenceplate: [%s]",
				licenceplate);
		logger.info(message1);
		Vehicle vehicle = findExistingVehicle(licenceplate);
		Map<String, Integer> position = new HashMap<String, Integer>();
		if (vehicle != null) {
			logger.info("Vehicle found, looking for the position");
			final Place vehiclePlace = vehicle.getPlace();
			try {
				final Integer levelNumber = vehiclePlace.getLevel().getLevelNumber();
				final Integer placeNumber = vehiclePlace.getPlaceNumber();
				position.put(Constant.LEVELNUMBER, levelNumber);
				position.put(Constant.PLACENUMBER, placeNumber);
			} catch (NullPointerException e) {
				logger.error("Vehicle position not found");
				System.err.println("Vehicle position not found");
			}
		}
		return position;
	}

	/**
	 * Exit an existing vehicle
	 * 
	 * @param licenceplate
	 */
	public void exitExistingVehicle(String licenceplate) {
		final StringBuilder sb = new StringBuilder("VehicleServicesImpl.exitExistingVehicle(licenceplate)");
		logger.warn(sb.toString());
		final String message1 = String.format("Trying to exit the vehicle with licenceplate: [%s]", licenceplate);
		logger.info(message1);
		Vehicle vehicle = findExistingVehicle(licenceplate);
		if (vehicle != null) {
			final Place place = vehicle.getPlace();
			place.setReserved(false);
			vehicleRepositoy.delete(vehicle);
			placeRepository.save(place);
		}
	}

	/**
	 * list all existing vehicle
	 * 
	 * @param
	 */
	public List<Vehicle> listAllVehicle() {
		final StringBuilder sb = new StringBuilder("VehicleServicesImpl.listAllVehicle(licenceplate)");
		logger.warn(sb.toString());
		logger.info("Listing all entred vehicle");
		return vehicleRepositoy.findAll();
	}

}
