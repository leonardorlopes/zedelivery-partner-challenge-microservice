package br.com.zedelivery.microservices.utils;

public class InputValidation {

	private static final String EMPTY = "";

	public static boolean isNullOrEmpty(String str) {

		if (str == null || EMPTY.equals(str)) {
			return true;
		}
		return false;
	}
}
