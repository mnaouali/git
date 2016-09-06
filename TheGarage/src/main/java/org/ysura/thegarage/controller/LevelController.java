/**
 * 
 */
package org.ysura.thegarage.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.ysura.thegarage.exception.ErrorResponse;
import org.ysura.thegarage.exception.TheGarageException;
import org.ysura.thegarage.model.Level;
import org.ysura.thegarage.model.Place;
import org.ysura.thegarage.services.LevelServices;
import org.ysura.thegarage.services.PlaceServices;

/**
 * @author mnaouali
 *
 */
@RestController
@RequestMapping("/rest/level")
public class LevelController {

	private static final Logger logger = LoggerFactory.getLogger(LevelController.class);

	@Autowired
	LevelServices levelServices;
	@Autowired
	PlaceServices placeServices;

	/**
	 * Add a new Level or add places to an existing level
	 * 
	 * @param levelNumber
	 * @param nbreCarSpace
	 * @param nbreMotorbikeSpace
	 */
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public ResponseEntity<Void> addLevel(@RequestParam("levelNumber") int levelNumber,
			@RequestParam("nbreCarSpace") int nbreCarSpace,
			@RequestParam("nbreMotorbikeSpace") int nbreMotorbikeSpace) throws TheGarageException{
		final StringBuilder sb = new StringBuilder(
				"LevelController.addLevel(levelNumber, nbreCarSpace, nbreMotorbikeSpace)");
		logger.warn(sb.toString());
		logger.info("Adding level with level number: " + levelNumber + " number CarSpace: " + nbreCarSpace
				+ " and numbre of MotorbikeSpace: " + nbreMotorbikeSpace);
		try {
			levelServices.addLevel(levelNumber, nbreCarSpace, nbreMotorbikeSpace);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * delete an existing level
	 * 
	 * @param levelNumber
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLevel(@RequestParam("levelNumber") int levelNumber) throws TheGarageException{
		final StringBuilder sb = new StringBuilder("LevelController.deleteLevel(levelNumber)");
		logger.warn(sb.toString());
		logger.info("Deletting level with level number: " + levelNumber);
		try {
			final boolean levelExist = levelServices.levelExist(levelNumber);
			if (levelExist) {
				final String message1 = String.format("Level [%d] is a valid level", levelNumber);
				logger.info(message1);
				logger.info("trying to get the number of levels before delete");
				final long numberOfLevels = levelServices.getNumberOfLevels();
				final String message2 = String.format("The number of levels is [%d]", numberOfLevels);
				logger.info(message2);
				if (numberOfLevels>1){
					logger.info("trying to check if there is still some reserved place in the level before the delete");
					final List<Place> reservedPlacesInALevel = placeServices.findAllReservedPlacesInALevel(levelNumber);
					if (reservedPlacesInALevel.isEmpty()){
						placeServices.deleteEmptyPlacesInALevel(levelNumber);
						final String message4 = String.format("All places in the level [%d] were deleted successfully", levelNumber);
						logger.info(message4);
						levelServices.deleteLevel(levelNumber);
						final String message5 = String.format("Level [%d] was deleted successfully", levelNumber);
						logger.info(message5);
						return new ResponseEntity<Void>(HttpStatus.OK);
					}
					final String errorMessage3 = String.format("You still have some reserved places in the requested level, try to exit them and try again!");
					logger.info(errorMessage3);
					throw new TheGarageException(errorMessage3);
				}
				final String errorMessage2 = String.format("You should at least keep one level!");
				logger.info(errorMessage2);
				throw new TheGarageException(errorMessage2);
			}
			final String errorMessage1 = String.format("The level number: [%d] doesn't exist",
					levelNumber);
			logger.info(errorMessage1);
			throw new TheGarageException(errorMessage1);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * List all levels details
	 * 
	 * @param
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Level>> getAllLevels() throws TheGarageException{
		final StringBuilder sb = new StringBuilder("LevelController.getAllLevels()");
		logger.warn(sb.toString());
		logger.info("Listing all levels details ");
		List<Level> levelList = new ArrayList<Level>();
		try {
			levelList = levelServices.listAllLevels();
			return new ResponseEntity<List<Level>>(levelList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Level>>(HttpStatus.INTERNAL_SERVER_ERROR);
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
