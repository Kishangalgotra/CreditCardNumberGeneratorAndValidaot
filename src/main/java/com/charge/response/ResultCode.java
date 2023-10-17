package com.charge.response;


public enum ResultCode {

	INVALID_CODE(-1, "Invalid code."),
	SUCCESS(0, "Success"),
	FAILURE(1, "Failure");

	private int errorCode;
	private String errorMessage;


	ResultCode(int errorCode, String errorMessage) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
	}

	public static ResultCode getErrorCode(Integer code) {
		for (ResultCode errorCode : ResultCode.values()) {
			if (code != null && errorCode.errorCode == code) {
				return errorCode;
			}
		}
		return INVALID_CODE;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
