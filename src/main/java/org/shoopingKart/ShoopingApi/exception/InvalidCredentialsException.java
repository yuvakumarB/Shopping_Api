package org.shoopingKart.ShoopingApi.exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Phone number or password";
	}
}
