package com.fourbrainsolutions.excel.importer;

import com.fourbrainsolutions.excel.exception.ExcelImportException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

public class ExcelImport {

	private static ExcelImport excelImport = null;

	private ExcelImport() {
		//Singleton

	}

	public static ExcelImport getInstance() {
		if (excelImport == null)
			excelImport = new ExcelImport();
		return excelImport;
	}

	public Workbook importExcel(String path, String filename) throws ExcelImportException {
		Workbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(new File(path + File.separator + filename));
			if (fis != null) {
				workbook = new XSSFWorkbookFactory().create(fis);
				System.out.println("Excel import successful");
			}
		} catch (Exception e) {
			throw new ExcelImportException("Failure during excel import", e);
		}
		return workbook;
	}
}
