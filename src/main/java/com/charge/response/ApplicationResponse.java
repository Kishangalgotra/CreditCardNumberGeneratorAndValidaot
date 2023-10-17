package com.charge.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationResponse<T> {

	@JsonProperty("response_code")
	private int code;

	@JsonProperty("response_message")
	private String message;

	@JsonProperty("payload")
	private T payload;

	public ApplicationResponse(int code, String message, T payload) {
		this.code = code;this.message = message;this.payload = payload;}

	public ApplicationResponse(int code, String message) {this.code = code;	this.message = message;}

	public ApplicationResponse() {}

	public static <T> ApplicationResponse<T> getSuccessResponse(T payload) {
		return new ApplicationResponse<T>(ResultCode.SUCCESS.getErrorCode(), ResultCode.SUCCESS.getErrorMessage(),
				payload);
	}

	public static <T> ApplicationResponse<T> getSuccessResponse(String message, T payload) {
		return new ApplicationResponse<T>(ResultCode.SUCCESS.getErrorCode(), message, payload);
	}

	public static <T> ApplicationResponse<T> getFailureResponse(String message, T payload) {
		return new ApplicationResponse<T>(ResultCode.FAILURE.getErrorCode(), message, payload);
	}
}
