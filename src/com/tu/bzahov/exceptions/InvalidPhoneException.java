package com.tu.bzahov.exceptions;

public class InvalidPhoneException extends Throwable {

	public static final String INVALID_PHONE = "Invalid Phone!!!";

	@Override
	public String getMessage() {
		return INVALID_PHONE;
	}
}
