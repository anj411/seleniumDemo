package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AdminExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(2);
		
		return row1;
	}
	
	public String getFirstName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellFname = row1.getCell(0);		
		String firstName = cellFname.getStringCellValue(); 
		return firstName;
	}
	
	public String getLastName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellLname = row1.getCell(1);
		String lastName = cellLname.getStringCellValue();  
		return lastName;
	}
	
	public String getEmail() throws IOException {
	
		XSSFRow row1 = getRow();
		XSSFCell cellEmail = row1.getCell(2);
		String email = cellEmail.getStringCellValue();
		return email;
	}
	
	public String getPassword() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPass = row1.getCell(3);
		String password = cellPass.getStringCellValue();
		return password;
	}
	
}
