/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.List;
import java.util.Map;

import org.ysura.thegarage.model.Vehicle;

/**
 * @author mnaouali
 *
 */
public interface VehicleServices {

	void enterNewVehicle(String licenceplate, String vehicleType, int placeNumber, int levelNumber,
			String placeType);

	Vehicle findExistingVehicle(String licenceplate);

	Map<String, Integer> findExistingVehiclePlace(String licenceplate);

	void exitExistingVehicle(String licenceplate);

	List<Vehicle> listAllVehicle();

}
