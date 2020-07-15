package com.fourbrainsolutions.excel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcel Berger - 4BrainSolutions GmbH
 */
public class ClassModel implements Serializable {
	private String className;
	private String capitalClassName;
	private String packageName;
	private List<PropertyModel> properties = new ArrayList<>();


	public String getCapitalClassName() {
		return className.toUpperCase();
	}

	public void addProperty(PropertyModel propertyModel) {
		this.properties.add(propertyModel);
	}

	public String getClassName() {
		char[] chars = className.toCharArray();
		char c = chars[0];
		c = Character.toUpperCase(c);
		chars[0] = c;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(chars);
		return stringBuilder.toString();
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<PropertyModel> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyModel> properties) {
		this.properties = properties;
	}
}
