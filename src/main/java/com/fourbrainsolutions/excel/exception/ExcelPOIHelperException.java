package com.fourbrainsolutions.excel.exception;

public class ExcelPOIHelperException extends Exception {
	public ExcelPOIHelperException(Throwable cause) {
		super(cause);
		System.err.println(cause.getMessage());
	}
}
