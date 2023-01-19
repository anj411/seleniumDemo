package com.excelTestData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExcelData {
	
	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(1);
		
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
	
	public String getMobileNumber() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellMobile = row1.getCell(4);
		String mobileNumber = cellMobile.getRawValue();
		return mobileNumber;
	}
	
	public String getPinCode() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellPin = row1.getCell(5);
		String pinCode = cellPin.getRawValue(); 
		return pinCode;
	}
	
	public String getCity() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellCity = row1.getCell(6);
		String city = cellCity.getStringCellValue();
		return city;
	}
	
	public String getAddress() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellAddress = row1.getCell(7);
		String address = cellAddress.getStringCellValue(); 
		return address;
	}
	
}

