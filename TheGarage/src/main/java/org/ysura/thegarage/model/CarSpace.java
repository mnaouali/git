/**
 * 
 */
package org.ysura.thegarage.model;

import org.ysura.thegarage.config.Constant;

/**
 * @author mnaouali
 *
 */
public class CarSpace extends Place{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param level
	 * @param placeNumber
	 * @param reserved
	 * @param vehicle
	 */
	public CarSpace(Level level, int placeNumber, boolean reserved) {
		super(level, placeNumber, reserved);
		this.type = Constant.CARSPACE;
	}
	/**
	 * @param type the type to set
	 */
	@Override
	void setType(String type) {
		this.type = Constant.CARSPACE;		
	}

}
