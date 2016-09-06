/**
 * 
 */
package org.ysura.thegarage.model;

import java.util.List;

/**
 * @author mnaouali
 *
 */
public class EmptyPlaces {
	
	private int levelNumber;
	private List<Integer> listPlaceNumber;
	private String placeType;
	/**
	 * 
	 */
	public EmptyPlaces() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param levelNumber
	 * @param listPlaceNumber
	 * @param placeType
	 */
	public EmptyPlaces(int levelNumber, List<Integer> listPlaceNumber, String placeType) {
		super();
		this.levelNumber = levelNumber;
		this.listPlaceNumber = listPlaceNumber;
		this.placeType = placeType;
	}
	/**
	 * @return the levelNumber
	 */
	public int getLevelNumber() {
		return levelNumber;
	}
	/**
	 * @param levelNumber the levelNumber to set
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	/**
	 * @return the listPlaceNumber
	 */
	public List<Integer> getListPlaceNumber() {
		return listPlaceNumber;
	}
	/**
	 * @param listPlaceNumber the listPlaceNumber to set
	 */
	public void setListPlaceNumber(List<Integer> listPlaceNumber) {
		this.listPlaceNumber = listPlaceNumber;
	}
	/**
	 * @return the placeType
	 */
	public String getPlaceType() {
		return placeType;
	}
	/**
	 * @param placeType the placeType to set
	 */
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmptyPlaces [levelNumber=" + levelNumber + ", listPlaceNumber=" + listPlaceNumber + ", placeType="
				+ placeType + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + levelNumber;
		result = prime * result + ((listPlaceNumber == null) ? 0 : listPlaceNumber.hashCode());
		result = prime * result + ((placeType == null) ? 0 : placeType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmptyPlaces other = (EmptyPlaces) obj;
		if (levelNumber != other.levelNumber)
			return false;
		if (listPlaceNumber == null) {
			if (other.listPlaceNumber != null)
				return false;
		} else if (!listPlaceNumber.equals(other.listPlaceNumber))
			return false;
		if (placeType == null) {
			if (other.placeType != null)
				return false;
		} else if (!placeType.equals(other.placeType))
			return false;
		return true;
	}
	

}
