package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static FileInputStream ip;
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getExcelData(String sheetname) {
		
		Object [][] data = null;
		
		try {
			 ip = new FileInputStream(".//src//test//resources//TestDataFolder//Testdata.xls");
		 book = WorkbookFactory.create(ip);
	 sheet = book.getSheet(sheetname);
	data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
	for(int i=0; i< sheet.getLastRowNum(); i++) {
		for(int j=0; j< sheet.getRow(0).getLastCellNum(); j++) {
			data [i][j] = sheet.getRow(i+1).getCell(j).toString();
		}
		
	}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}
}
