/**
 * 
 */
package org.ysura.thegarage.model;

import org.ysura.thegarage.config.Constant;

/**
 * @author mnaouali
 *
 */
public class Car extends Vehicle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param licenceplate
	 * @param location
	 */
	public Car(String licenceplate, Place place) {
		super(licenceplate, place);
		this.type = Constant.CAR;
	}
	/**
	 * @param type the type to set
	 */
	@Override
	public void setType(String type) {
		this.type = Constant.CAR;
	}

}
