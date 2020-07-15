package com.fourbrainsolutions.excel.exception;

/**
 * @author Marcel Berger - 4BrainSolutions GmbH
 */
public class TemplateLoaderException extends Exception {
	public TemplateLoaderException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public TemplateLoaderException(String exceptionMessage, Throwable throwable) {
		super(exceptionMessage, throwable);
	}
}
