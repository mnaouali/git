/**
 * 
 */
package org.ysura.thegarage.model;

import org.ysura.thegarage.config.Constant;

/**
 * @author mnaouali
 *
 */
public class MotorbikeSpace extends Place{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param placeNumber
	 * @param reserved
	 */
	public MotorbikeSpace(Level level, int placeNumber, boolean reserved) {
		super(level, placeNumber, reserved);
		this.type = Constant.MOTORBIKESPACE;
	}
	/**
	 * @param type the type to set
	 */
	@Override
	void setType(String type) {
		this.type = Constant.MOTORBIKESPACE;
		
	}
	

}
