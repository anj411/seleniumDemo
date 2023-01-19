package com.testProject.testDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Demo {

	public static void main(String[] args) throws IOException {
		getCategoryImage();
	}
	
	public static XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(5);
		
		return row1;
	}
	
	public static String getCategoryName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellfname = row1.getCell(0);		
		String categoryName = cellfname.getStringCellValue(); 
		System.out.println("categoryName is"+ categoryName);
		return categoryName;
	}
	
	public static String getCategoryImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell celllname = row1.getCell(1);
		String categoryImage = celllname.getStringCellValue();  
		System.out.println("categoryImage is"+ categoryImage);
		return categoryImage;
	}
	
}
