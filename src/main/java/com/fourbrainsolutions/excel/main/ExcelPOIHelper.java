package com.fourbrainsolutions.excel.main;

import com.fourbrainsolutions.excel.exception.ExcelImportException;
import com.fourbrainsolutions.excel.exception.ExcelPOIHelperException;
import com.fourbrainsolutions.excel.exception.FileCreatorException;
import com.fourbrainsolutions.excel.filecreator.FileCreator;
import com.fourbrainsolutions.excel.importer.ExcelImport;
import com.fourbrainsolutions.excel.model.ClassModel;
import com.fourbrainsolutions.excel.utils.Config;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Marcel Berger - 4BrainSolutions GmbH
 */
public class ExcelPOIHelper {

	public static void main(String[] args) throws ExcelPOIHelperException {
		String excelFilePath = Config.getValue("excelFilePath");
		String excelFileName = Config.getValue("excelFileName");
		Integer excelSheetNummer = Integer.valueOf(Config.getValue("excelSheetNummer"));
		Integer excelHeaderRowNr = Integer.valueOf(Config.getValue("excelHeaderRowNr"));

		String filePackage = Config.getValue("filePackage");
		String filename = Config.getValue("filename");
		String filePath = Config.getValue("filePath");

		Workbook workbook = null;
		try {
			workbook = ExcelImport.getInstance().importExcel(excelFilePath, excelFileName);
		} catch (ExcelImportException e) {
			throw new ExcelPOIHelperException(e);
		}

		Sheet sheet = workbook.getSheetAt(--excelSheetNummer);
		Row headerRow = sheet.getRow(--excelHeaderRowNr);

		FileCreator fileCreator = FileCreator.getInstance();
		try {
			ClassModel classModel = fileCreator.mapRowToClassModel(headerRow, filePackage, filename);
			fileCreator.writeClassFile(classModel, filePath, filename, "classtemplate.ftl");

			fileCreator.writeClassFile(classModel, filePath, filename + "Entity", "classtemplateentity.ftl");

		} catch (FileCreatorException e) {
			throw new ExcelPOIHelperException(e);
		}
	}
}
