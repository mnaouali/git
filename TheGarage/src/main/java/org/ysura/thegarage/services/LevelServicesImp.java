/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysura.thegarage.model.CarSpace;
import org.ysura.thegarage.model.Level;
import org.ysura.thegarage.model.MotorbikeSpace;
import org.ysura.thegarage.repositories.LevelRepository;
import org.ysura.thegarage.repositories.PlaceRepository;

/**
 * @author mnaouali
 *
 */
@Service
@Transactional
public class LevelServicesImp implements LevelServices {

	private static final Logger logger = LoggerFactory.getLogger(LevelServicesImp.class);

	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private PlaceRepository placeRepository;
	

	/**
	 * Add a new Level or add places to an existing level
	 * 
	 * @param levelNumber
	 * @param nbreCarSpace
	 * @param nbreMotorbikeSpace
	 */
	public void addLevel(int levelNumber, int nbreCarSpace, int nbreMotorbikeSpace) {
		final StringBuilder sb = new StringBuilder(
				"LevelServicesImp.addLevel(levelNumber, nbreCarSpace, nbreMotorbikeSpace)");
		logger.warn(sb.toString());
		final String message1 = String.format("Looking for the level number : [%d] if exists", levelNumber);
		logger.info(message1);
		final Level level = findExistingLevel(levelNumber);
		if (level != null) {
			final String message2 = String.format("Level [%d] is an existing level", levelNumber);
			logger.info(message2);
			logger.info("Updating level places");
			final Integer nbreCarSpace2 = level.getNbreCarSpace();
			for (int i = 0; i < nbreCarSpace; i++) {
				final int carPlaceNumber = nbreCarSpace2 + 1 + i;
				placeRepository.save(new CarSpace(level, carPlaceNumber, false));
			}
			final int nbreMotorbikeSpace2 = level.getNbreMotorbikeSpace();
			for (int i = 0; i < nbreMotorbikeSpace; i++) {
				final int motorbikePlaceNumber = nbreMotorbikeSpace2 + 1 + i;
				placeRepository.save(new MotorbikeSpace(level, motorbikePlaceNumber, false));
			}
			level.setNbreCarSpace(nbreCarSpace2 + nbreCarSpace);
			level.setNbreMotorbikeSpace(nbreMotorbikeSpace2 + nbreMotorbikeSpace);
			levelRepository.save(level);
		} else {
			final String message3 = String.format("Level [%d] is a new level", levelNumber);
			logger.info(message3);
			logger.info("Creating a new level ");
			Level newLevel = new Level(levelNumber, nbreCarSpace, nbreMotorbikeSpace);
			for (int i = 0; i < nbreCarSpace; i++) {
				placeRepository.save(new CarSpace(newLevel, i, false));
			}
			for (int i = 0; i < nbreMotorbikeSpace; i++) {
				placeRepository.save(new MotorbikeSpace(newLevel, i, false));
			}
			levelRepository.save(newLevel);
		}
	}

	/**
	 * Looking for an existing level
	 * 
	 * @param levelNumber
	 */
	public boolean levelExist(int levelNumber) {
		final StringBuilder sb = new StringBuilder("LevelServicesImp.levelExist(levelNumber)");
		logger.warn(sb.toString());
		final String message1 = String.format("Looking for the level number : [%d] if exists", levelNumber);
		logger.info(message1);
		boolean levelExist = false;
		final Level level = findExistingLevel(levelNumber);
		if (level != null) {
			final String message2 = String.format("Level [%d] is a valid level", levelNumber);
			logger.info(message2);
			levelExist = true;
		}
		return levelExist;
	}

	/**
	 * retrieve the number of levels in the garage
	 * 
	 * @param
	 */
	public long getNumberOfLevels() {
		final StringBuilder sb = new StringBuilder("LevelServicesImp.getNumberOfLevels()");
		logger.warn(sb.toString());
		logger.info("Trying to get the number of levels");
		final long numberOfLevels = levelRepository.count();
		return numberOfLevels;
	}

	/**
	 * delete an existing level
	 * 
	 * @param levelNumber
	 */
	public void deleteLevel(int levelNumber) {
		final StringBuilder sb = new StringBuilder("LevelServicesImp.deleteLevel(levelNumber)");
		logger.warn(sb.toString());
		logger.info("Trying to delete a level");
		final Level level = findExistingLevel(levelNumber);
		levelRepository.delete(level);
	}

	/**
	 * List all levels details
	 * 
	 * @param
	 */
	public List<Level> listAllLevels() {
		final StringBuilder sb = new StringBuilder("LevelServicesImp.listAllLevels()");
		logger.warn(sb.toString());
		logger.info("Retrieving the list of all levels");
		return levelRepository.findAll();
	}

	/**
	 * Search for a level using his levelNumber
	 * 
	 * @param levelNumber
	 */
	public Level findExistingLevel(int levelNumber) {
		final StringBuilder sb = new StringBuilder("LevelServicesImp.findExistingLevel(levelNumber)");
		logger.warn(sb.toString());
		final String message1 = String.format("Looking for the level number : [%d] if exists", levelNumber);
		logger.info(message1);
		final Level level = levelRepository.findByLevelNumber(levelNumber);
		return level;
	}

}
