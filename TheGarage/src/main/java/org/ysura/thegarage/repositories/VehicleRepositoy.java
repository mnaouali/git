/**
 * 
 */
package org.ysura.thegarage.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ysura.thegarage.model.Vehicle;

/**
 * @author mnaouali
 *
 */
@Repository
public interface VehicleRepositoy extends MongoRepository<Vehicle, String>{
	Vehicle findByLicenceplate (String licenceplate);

}
