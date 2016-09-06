/**
 * 
 */
package org.ysura.thegarage.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mnaouali
 *
 */
@Document(collection = "parking_levels")
public class Level  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer levelNumber;
	private Integer nbreCarSpace;
	private Integer nbreMotorbikeSpace;
	/**
	 * 
	 */
	public Level() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param levelNumber
	 * @param nbreCarSpace
	 * @param nbreMotorbikeSpace
	 */
	public Level(Integer levelNumber, Integer nbreCarSpace, Integer nbreMotorbikeSpace) {
		super();
		this.levelNumber = levelNumber;
		this.nbreCarSpace = nbreCarSpace;
		this.nbreMotorbikeSpace = nbreMotorbikeSpace;
	}
	/**
	 * @return the levelNumber
	 */
	public Integer getLevelNumber() {
		return levelNumber;
	}
	/**
	 * @param levelNumber the levelNumber to set
	 */
	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}
	/**
	 * @return the nbreCarSpace
	 */
	public Integer getNbreCarSpace() {
		return nbreCarSpace;
	}
	/**
	 * @param nbreCarSpace the nbreCarSpace to set
	 */
	public void setNbreCarSpace(Integer nbreCarSpace) {
		this.nbreCarSpace = nbreCarSpace;
	}
	/**
	 * @return the nbreMotorbikeSpace
	 */
	public Integer getNbreMotorbikeSpace() {
		return nbreMotorbikeSpace;
	}
	/**
	 * @param nbreMotorbikeSpace the nbreMotorbikeSpace to set
	 */
	public void setNbreMotorbikeSpace(Integer nbreMotorbikeSpace) {
		this.nbreMotorbikeSpace = nbreMotorbikeSpace;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Level [levelNumber=" + levelNumber + ", nbreCarSpace=" + nbreCarSpace + ", nbreMotorbikeSpace="
				+ nbreMotorbikeSpace + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((levelNumber == null) ? 0 : levelNumber.hashCode());
		result = prime * result + ((nbreCarSpace == null) ? 0 : nbreCarSpace.hashCode());
		result = prime * result + ((nbreMotorbikeSpace == null) ? 0 : nbreMotorbikeSpace.hashCode());
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
		Level other = (Level) obj;
		if (levelNumber == null) {
			if (other.levelNumber != null)
				return false;
		} else if (!levelNumber.equals(other.levelNumber))
			return false;
		if (nbreCarSpace == null) {
			if (other.nbreCarSpace != null)
				return false;
		} else if (!nbreCarSpace.equals(other.nbreCarSpace))
			return false;
		if (nbreMotorbikeSpace == null) {
			if (other.nbreMotorbikeSpace != null)
				return false;
		} else if (!nbreMotorbikeSpace.equals(other.nbreMotorbikeSpace))
			return false;
		return true;
	}

	
}
