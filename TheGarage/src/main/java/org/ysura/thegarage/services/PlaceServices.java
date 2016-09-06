/**
 * 
 */
package org.ysura.thegarage.services;

import java.util.List;
import java.util.Map;

import org.ysura.thegarage.model.EmptyPlaces;
import org.ysura.thegarage.model.Place;

/**
 * @author mnaouali
 *
 */
public interface PlaceServices {

	List<Place> listAllPlaces();

	void deleteEmptyPlacesInALevel(int levelNumber);

	List<EmptyPlaces> listEmptyplacesByType(String placeType);

	boolean placeIsReserved(int placeNumber, int levelNumber, String placeType);

	List<Place> findAllReservedPlacesInALevel(int levelNumber);

	Place placeExist(int placeNumber, int levelNumber, String placeType);

	void deletePlace(int placeNumber, int levelNumber, String placeType);

	Map<String, Integer> numberOfEmptyPlaces();
}
