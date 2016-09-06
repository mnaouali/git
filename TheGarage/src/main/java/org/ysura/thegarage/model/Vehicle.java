/**
 * 
 */
package org.ysura.thegarage.model;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mnaouali
 *
 */
@Document(collection= "vehicle")
public abstract class Vehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String licenceplate;
	@DBRef
	private Place place;
	protected String type;
	/**
	 * 
	 */
	public Vehicle() {
		super();
	}
	/**
	 * @param licenceplate
	 * @param place
	 */
	public Vehicle(String licenceplate, Place place) {
		super();
		this.licenceplate = licenceplate;
		this.place = place;
	}
	/**
	 * @return the licenceplate
	 */
	public String getLicenceplate() {
		return licenceplate;
	}
	/**
	 * @param licenceplate the licenceplate to set
	 */
	public void setLicenceplate(String licenceplate) {
		this.licenceplate = licenceplate;
	}
	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	abstract void setType(String type);
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicle [licenceplate=" + licenceplate + ", place=" + place + ", type=" + type + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licenceplate == null) ? 0 : licenceplate.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (licenceplate == null) {
			if (other.licenceplate != null)
				return false;
		} else if (!licenceplate.equals(other.licenceplate))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
