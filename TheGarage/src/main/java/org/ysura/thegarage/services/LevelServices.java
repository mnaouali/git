/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.List;

import org.ysura.thegarage.model.Level;

/**
 * @author mnaouali
 *
 */
public interface LevelServices {

	Level findExistingLevel(int levelNumber);

	void addLevel(int levelNumber, int nbreCarSpace, int nbreMotorbikeSpace);
	
	long getNumberOfLevels ();

	void deleteLevel(int levelNumber);

	List<Level> listAllLevels();

	boolean levelExist(int levelNumber);

}
