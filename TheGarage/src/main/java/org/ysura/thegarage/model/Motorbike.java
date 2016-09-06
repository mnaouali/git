/**
 * 
 */
package org.ysura.thegarage.model;

import org.ysura.thegarage.config.Constant;

/**
 * @author mnaouali
 *
 */
public class Motorbike extends Vehicle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param licenceplate
	 * @param location
	 */
	public Motorbike(String licenceplate, Place place) {
		super(licenceplate, place);
		this.type = Constant.MOTORBIKE;
	}
	/**
	 * @param type the type to set
	 */
	@Override
	public void setType(String type) {
		this.type = Constant.MOTORBIKE;
	}
		
}
