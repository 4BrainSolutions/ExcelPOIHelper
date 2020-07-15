package com.fourbrainsolutions.excel.filecreator;

import freemarker.template.TemplateExceptionHandler;

public class Configuration extends freemarker.template.Configuration {
	// Encoding
	public static final String ENCODING = "UTF-8";

	public Configuration(String encoding, Class templateLoadingClass) {
		// Config for FreemakerTemplate
		super(Configuration.VERSION_2_3_28);
		this.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/");
		this.setDefaultEncoding(encoding);
		this.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		this.setLogTemplateExceptions(false);
		this.setWrapUncheckedExceptions(true);
	}
}
