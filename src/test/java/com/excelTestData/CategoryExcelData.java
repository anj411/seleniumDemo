package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CategoryExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(5);
		
		return row1;
		
	}
	
	public String getCategoryName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellName = row1.getCell(0);		
		String categoryName = cellName.getStringCellValue(); 
		return categoryName;
		
	}
	
	public String getCategoryImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellImage = row1.getCell(1);
		String categoryImage = cellImage.getStringCellValue();  
		return categoryImage;
		
	}
	
	
}
