/**
 * 
 */
package org.ysura.thegarage.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ysura.thegarage.model.Level;
import org.ysura.thegarage.model.Place;

/**
 * @author mnaouali
 *
 */
@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

	List<Place> findByReservedIsFalse();

	List<Place> findByReservedIsTrueAndLevel(Level level);

	Place findByPlaceNumberAndLevelAndType(int placeNumber, Level level, String type);

	List<Place> findByTypeLike(String type);

	List<Place> findByReservedIsFalseAndTypeLike(String type);
	
	List<Place> findByLevel (Level level);
	
	List<Place> findByReservedIsFalseAndTypeAndLevel(String type, Level level);

}
