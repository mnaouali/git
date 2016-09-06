/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ysura.thegarage.config.Constant;
import org.ysura.thegarage.model.EmptyPlaces;
import org.ysura.thegarage.model.Level;
import org.ysura.thegarage.model.Place;
import org.ysura.thegarage.repositories.LevelRepository;
import org.ysura.thegarage.repositories.PlaceRepository;

/**
 * @author mnaouali
 *
 */
@Service
@Transactional
public class PlaceServicesImpl implements PlaceServices {

	private static final Logger logger = LoggerFactory.getLogger(PlaceServicesImpl.class);

	@Autowired
	private LevelServices levelServices;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private LevelRepository levelRepository;

	private static final String CARSPACES = "CarSpaces";
	private static final String MOTORBIKESPACES = "MotorbikeSpaces";

	/**
	 * Retrieve the list of all existing places
	 * 
	 * @param
	 */
	public List<Place> listAllPlaces() {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.listAllPlaces()");
		logger.warn(sb.toString());
		logger.info("Getting the list of all places");
		final List<Place> placesList = placeRepository.findAll();
		return placesList;
	}

	/**
	 * Retrieve the list of reserved places in a given level
	 * 
	 * @param levelNumber
	 */
	public List<Place> findAllReservedPlacesInALevel(int levelNumber) {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.findAllReservedPlacesInALevel(levelNumber)");
		logger.warn(sb.toString());
		List<Place> reservedPlaces = new ArrayList<Place>();
		final String message1 = String.format("Looking for reserved places in the level number: [%d]", levelNumber);
		logger.info(message1);
		final Level level = levelServices.findExistingLevel(levelNumber);
		if (level != null) {
			final String message2 = String.format("Level [%d] is a valid level", levelNumber);
			logger.info(message2);
			reservedPlaces = placeRepository.findByReservedIsTrueAndLevel(level);
		}
		return reservedPlaces;
	}
	
	public List<Place> listEmptyLevelPlacesByType (Level level, String placeType){
		final List<Place> emptyPlaces = placeRepository.findByReservedIsFalseAndTypeAndLevel(placeType, level);
		return emptyPlaces;
	}

	/**
	 * Retrieve the list of empty places
	 * 
	 * @param
	 */
	public List<EmptyPlaces> listEmptyplacesByType(String placeType) {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.listEmptyplaces()");
		logger.warn(sb.toString());
		logger.info("Getting the list of all empty places by placeType");
		List<EmptyPlaces> emptyPlacesList = new ArrayList<EmptyPlaces>();
		final List<Level> levelsList = levelServices.listAllLevels();
		for (Level level:levelsList){
			EmptyPlaces emptyPlaces = new EmptyPlaces();
			final Integer levelNumber2 = level.getLevelNumber();
			emptyPlaces.setLevelNumber(levelNumber2);
			final List<Place> emptySpaces = listEmptyLevelPlacesByType(level, placeType);
			List<Integer> listPlaceNumber = new ArrayList<Integer>();
			for (Place place : emptySpaces) {
				final Integer placeNumber = place.getPlaceNumber();
				listPlaceNumber.add(placeNumber);
			}
			emptyPlaces.setListPlaceNumber(listPlaceNumber);
			emptyPlaces.setPlaceType(placeType);
			emptyPlacesList.add(emptyPlaces);
		}
		return emptyPlacesList;
	}

	/**
	 * Retrieve the number of empty places
	 * 
	 * @param
	 */
	public Map<String, Integer> numberOfEmptyPlaces() {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.listEmptyplaces()");
		logger.warn(sb.toString());
		Map<String, Integer> emptyPlaces = new HashMap<String, Integer>();
		logger.info("Getting the number of empty places by type");
		final List<Place> emptyCarSpaces = placeRepository.findByReservedIsFalseAndTypeLike(Constant.CARSPACE);
		final List<Place> emptyMotorbikeSpaces = placeRepository
				.findByReservedIsFalseAndTypeLike(Constant.MOTORBIKESPACE);
		final int numberOfEmptyCarSpaces = emptyCarSpaces.size();
		final int numberOfEmptyMotorbikeSpaces = emptyMotorbikeSpaces.size();
		emptyPlaces.put(CARSPACES, numberOfEmptyCarSpaces);
		emptyPlaces.put(MOTORBIKESPACES, numberOfEmptyMotorbikeSpaces);
		return emptyPlaces;
	}

	/**
	 * Check if a place is reserved or not
	 * 
	 * @param placeNumber
	 * @param levelNumber
	 * @param placeType
	 */
	public boolean placeIsReserved(int placeNumber, int levelNumber, String placeType) {
		final StringBuilder sb = new StringBuilder(
				"PlaceServicesImpl.placeIsReserved(placeNumber, levelNumber, placeType)");
		logger.warn(sb.toString());
		final String message1 = String.format("Checking if the [%s] number : [%d] in the level: [%d] is reserved",
				placeType, placeNumber, levelNumber);
		logger.info(message1);
		boolean reservedStatus = true;
		final Place placeExist = placeExist(placeNumber, levelNumber, placeType);
		if (placeExist != null) {
			final String message2 = String.format("The [%s] is a valid place", placeType);
			logger.info(message2);
			reservedStatus = placeExist.isReserved();
		}
		return reservedStatus;
	}

	/**
	 * Check for an existing place
	 * 
	 * @param placeNumber
	 * @param levelNumber
	 * @param placeType
	 */
	public Place placeExist(int placeNumber, int levelNumber, String placeType) {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.placeExist(placeNumber, levelNumber, placeType)");
		logger.warn(sb.toString());
		final String message1 = String.format("Checking if the [%s] number : [%d] in the level: [%d] is valid",
				placeType, placeNumber, levelNumber);
		logger.info(message1);
		Place place = null;
		try {
			final Level level = levelRepository.findByLevelNumber(levelNumber);
			place = placeRepository.findByPlaceNumberAndLevelAndType(placeNumber, level, placeType);
		} catch (Exception e) {
			final String message2 = String.format("There is no place with the number : [%d] in the level : [%d]",
					placeNumber, levelNumber);
			System.err.println(message2);
		}
		return place;
	}
	
	/**
	 * Check for an existing place
	 * 
	 * @param placeNumber
	 * @param levelNumber
	 * @param placeType
	 */
	public void deletePlace(int placeNumber, int levelNumber, String placeType){
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.deletePlace(placeNumber, levelNumber, placeType)");
		logger.warn(sb.toString());
		final String message1 = String.format("Trying to delete the [%s] number : [%d] in the level: [%d] is valid",
				placeType, placeNumber, levelNumber);
		logger.info(message1);
		final Level level = levelRepository.findByLevelNumber(levelNumber);
		final Place place = placeRepository.findByPlaceNumberAndLevelAndType(placeNumber, level, placeType);
		placeRepository.delete(place);
		
	}

	/**
	 * Delete empty places in a given level
	 * 
	 * @param levelNumber
	 */
	public void deleteEmptyPlacesInALevel(int levelNumber) {
		final StringBuilder sb = new StringBuilder("PlaceServicesImpl.deleteEmptyPlacesInALevel(levelNumber)");
		logger.warn(sb.toString());
		final String message1 = String.format("Trying to delete empty places in the level: [%d]", levelNumber);
		logger.info(message1);
		final Level level = levelRepository.findByLevelNumber(levelNumber);
		final List<Place> placeToBeDeleted = placeRepository.findByLevel(level);
		final String message2 = String.format("deleting empty places in the level: [%d]", levelNumber);
		logger.info(message2);
		placeRepository.delete(placeToBeDeleted);
	}

}
