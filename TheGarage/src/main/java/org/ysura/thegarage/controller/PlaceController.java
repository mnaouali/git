/**
 * 
 */
package org.ysura.thegarage.controller;

import java.util.List;
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
import org.ysura.thegarage.exception.ErrorResponse;
import org.ysura.thegarage.exception.TheGarageException;
import org.ysura.thegarage.model.EmptyPlaces;
import org.ysura.thegarage.model.Place;
import org.ysura.thegarage.services.LevelServices;
import org.ysura.thegarage.services.PlaceServices;

/**
 * @author mnaouali
 *
 */
@RestController
@RequestMapping("/rest/place")
public class PlaceController {

	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

	@Autowired
	PlaceServices placeServices;
	@Autowired
	LevelServices levelServices;

	/**
	 * Retrieve the number of empty places
	 * 
	 * @param
	 */
	@RequestMapping(value = "/numberofemptyplaces", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Integer>> getNumberOfEmptyPlaces() throws TheGarageException{
		final StringBuilder sb = new StringBuilder("PlaceController.getNumberOfEmptyPlaces()");
		logger.warn(sb.toString());
		logger.info("Getting the number of empty places by type");
		try {
			final Map<String, Integer> numberOfEmptyPlaces = placeServices.numberOfEmptyPlaces();
			return new ResponseEntity<Map<String, Integer>>(numberOfEmptyPlaces, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Retrieve the list of empty places
	 * 
	 * @param
	 */
	@RequestMapping(value = "/listofemptyplacesbytype", method = RequestMethod.GET)
	public ResponseEntity<List<EmptyPlaces>> listOfEmptyPlacesByType(@RequestParam("placeType") String placeType) throws TheGarageException{
		final StringBuilder sb = new StringBuilder("PlaceController.listOfEmptyPlaces()");
		logger.warn(sb.toString());
		logger.info("Getting the list of all empty places");
		try {
			final List<EmptyPlaces> listOfEmptyPlaces = placeServices.listEmptyplacesByType(placeType);
			return new ResponseEntity<List<EmptyPlaces>>(listOfEmptyPlaces, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<EmptyPlaces>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Delete empty places in a given level
	 * 
	 * @param
	 */
	@RequestMapping(value = "/deleteplace", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePlace(@RequestParam("levelNumber") int levelNumber,
			@RequestParam("placeNumber") int placeNumber, @RequestParam("placeType") String placeType) throws TheGarageException{
		final StringBuilder sb = new StringBuilder("PlaceController.deletePlace(levelNumber, placeNumber, placeType)");
		logger.warn(sb.toString());
		final String message1 = String.format("Trying to delete the [%s] number: [%d] in the level [%d]", placeType,
				placeNumber, levelNumber);
		logger.info(message1);
		try {
			final boolean levelExist = levelServices.levelExist(levelNumber);
			if (levelExist) {
				final Place place = placeServices.placeExist(placeNumber, levelNumber, placeType);
				if (place != null) {
					final boolean isReserved = placeServices.placeIsReserved(placeNumber, levelNumber, placeType);
					if (!isReserved) {
						placeServices.deletePlace(placeNumber, levelNumber, placeType);
						return new ResponseEntity<Void>(HttpStatus.OK);
					}
					final String errorMessage3 = String.format(
							"The [%s] number: [%d] in the level [%d] is still reserved and can't be deleted", placeType,
							placeNumber, levelNumber);
					logger.info(errorMessage3);
					throw new TheGarageException(errorMessage3);
				}
				final String errorMessage2 = String.format("The [%s] number: [%d] doesn't exist in the level [%d]",
						placeType, placeNumber, levelNumber);
				logger.info(errorMessage2);
				throw new TheGarageException(errorMessage2);
			}
			final String errorMessage1 = String.format("The level number: [%d] doesn't exist", levelNumber);
			logger.info(errorMessage1);
			throw new TheGarageException(errorMessage1);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
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
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

	}

}
