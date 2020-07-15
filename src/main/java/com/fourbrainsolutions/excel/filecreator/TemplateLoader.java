package com.fourbrainsolutions.excel.filecreator;

import com.fourbrainsolutions.excel.exception.TemplateLoaderException;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author Marcel Berger - 4BrainSolutions GmbH
 */
public class TemplateLoader {
	private Configuration configuration = null;

	public TemplateLoader(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @param templateName
	 * @return
	 * @throws TemplateLoaderException
	 */
	public Template loadTemplate(String templateName) throws TemplateLoaderException {
		Template template = null;
		try {
			template = configuration.getTemplate(templateName);
		} catch (IOException e) {
			throw new TemplateLoaderException("Failure during read template from file " + templateName, e);
		}
		return template;
	}
}
