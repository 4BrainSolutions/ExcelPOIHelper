package com.fourbrainsolutions.excel.filecreator;

import com.fourbrainsolutions.excel.exception.FileCreatorException;
import com.fourbrainsolutions.excel.exception.TemplateLoaderException;
import com.fourbrainsolutions.excel.main.ExcelPOIHelper;
import com.fourbrainsolutions.excel.model.ClassModel;
import com.fourbrainsolutions.excel.model.PropertyModel;
import com.fourbrainsolutions.excel.utils.Config;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileCreator {

	private static FileCreator fileCreator = null;
	private final Configuration configuration = new Configuration(Configuration.ENCODING, ExcelPOIHelper.class);
	private List<String> entriesCheck;
	private List<String> duplicated;

	private FileCreator() {
		//Singleton
	}

	public static FileCreator getInstance() {
		if (fileCreator == null)
			fileCreator = new FileCreator();
		return fileCreator;
	}

	public ClassModel mapRowToClassModel(Row row, String packageName, String className) throws FileCreatorException {
		entriesCheck = new ArrayList<>();
		duplicated = new ArrayList<>();
		ClassModel classModel = new ClassModel();
		classModel.setPackageName(packageName);
		classModel.setClassName(className);
		int counter = 0;
		for (Iterator<Cell> i = row.iterator(); i.hasNext(); ) {
			if (counter < (Integer.valueOf(Config.getValue("excelFirstColumn")) - 1)) {
				counter++;
				continue;
			}
			Cell cell = i.next();

			if (cell.getStringCellValue().isEmpty())
				continue;

			if (entriesCheck.contains(cell.getStringCellValue())) {
				System.err.println(cell.getStringCellValue() + " already exists");
				duplicated.add(cell.getStringCellValue());
				continue;
			}
			entriesCheck.add(cell.getStringCellValue());
			PropertyModel propertyModel = new PropertyModel();
			propertyModel.setPropertyName(correctPropertyName(cell.getStringCellValue()));
			propertyModel.setPropertyNameOriginal(cell.getStringCellValue());
			propertyModel.setPropertyType("String");
			classModel.addProperty(propertyModel);
		}
		System.out.println("Mapping complete..");
		if (!duplicated.isEmpty())
			writeDuplicateLog(duplicated);
		return classModel;
	}

	private void writeDuplicateLog(List<String> entriesCheck) throws FileCreatorException {

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(Config.getValue("filePath") + File.separator + "duplicatedLog.txt"));

			for (String s : entriesCheck) {
				writer.append(s);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			throw new FileCreatorException("Error during write log", e);
		}
	}

	private String correctPropertyName(String value) {
		StringBuilder result = new StringBuilder();
		value = value.toLowerCase();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];
			if ((c == ' ' || c == '_') && i + 1 <= chars.length) {
				char next = chars[++i];
				String upperCase = String.valueOf(next).toUpperCase();
				result.append(upperCase);
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}

	public void writeClassFile(ClassModel classModel, String pathName, String fileName, String templateName) throws FileCreatorException {
		TemplateLoader templateLoader = new TemplateLoader(configuration);
		Template classTemplate = null;
		try {
			classTemplate = templateLoader.loadTemplate(templateName);
		} catch (TemplateLoaderException e) {
			throw new FileCreatorException("Failure during loading template", e);
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(pathName + File.separator + fileName + ".java");
		} catch (IOException e) {
			throw new FileCreatorException("Failure during create filewriter", e);
		}

		try {
			classTemplate.process(classModel, fileWriter);
		} catch (TemplateException | IOException e) {
			throw new FileCreatorException("Failure during process template", e);
		}
		System.out.println("File " + fileName + ".java generated");
	}
}
