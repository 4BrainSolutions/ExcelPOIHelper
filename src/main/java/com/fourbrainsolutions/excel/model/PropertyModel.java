package com.fourbrainsolutions.excel.model;

import java.io.Serializable;

/**
 * @author Marcel Berger - 4BrainSolutions GmbH
 */
public class PropertyModel implements Serializable {
	private String propertyNameOriginal;
	private String propertyMethodName;
	private String propertyName;
	private String propertyType;

	public String getPropertyCapitalName() {
		return propertyName.toUpperCase();
	}

	public String getPropertyMethodName() {
		char[] chars = propertyName.toCharArray();
		char c = chars[0];
		c = Character.toUpperCase(c);
		chars[0] = c;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(chars);
		return stringBuilder.toString();
	}

	public String getPropertyNameOriginal() {
		return propertyNameOriginal;
	}

	public void setPropertyNameOriginal(String propertyNameOriginal) {
		this.propertyNameOriginal = propertyNameOriginal;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
}
