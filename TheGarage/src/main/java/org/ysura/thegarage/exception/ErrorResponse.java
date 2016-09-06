/**
 * 
 */
package org.ysura.thegarage.exception;

/**
 * @author mnaouali
 *
 */
public class ErrorResponse {
	private int errorCode;
	private String message;
	/**
	 * 
	 */
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorResponse(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.message = errorMessage;
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	

}
