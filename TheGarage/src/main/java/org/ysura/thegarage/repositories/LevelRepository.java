/**
 * 
 */
package org.ysura.thegarage.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ysura.thegarage.model.Level;

/**
 * @author mnaouali
 *
 */
@Repository
public interface LevelRepository extends MongoRepository<Level, String> {
	Level findByLevelNumber(Integer levelNumber);
}
