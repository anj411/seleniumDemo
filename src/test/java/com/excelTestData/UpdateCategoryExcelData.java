package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateCategoryExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(8);
		
		return row1;
	}
	
	public String getUpCategoryName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellName = row1.getCell(0);		
		String upcategoryName = cellName.getStringCellValue(); 
		return upcategoryName;
		
	}
	
	public String getUpCategoryImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellImage = row1.getCell(1);
		String upCategoryImage = cellImage.getStringCellValue();  
		return upCategoryImage;
		
	}
}
