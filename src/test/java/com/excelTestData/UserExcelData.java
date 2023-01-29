package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Constant;

public class UserExcelData {
	
	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\UserData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(Constant.cell_0);
		
		XSSFRow row1 = sheet.getRow(Constant.cell_1);
		
		return row1;
	}
	
	public String getUserFirstName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellFname = row1.getCell(Constant.cell_0);		
		String firstName = cellFname.getStringCellValue(); 
		return firstName;
	}
	
	public String getUserLastName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellLname = row1.getCell(Constant.cell_1);
		String lastName = cellLname.getStringCellValue();  
		return lastName;
	}
	
	public String getUserEmail() throws IOException {
	
		XSSFRow row1 = getRow();
		XSSFCell cellEmail = row1.getCell(Constant.cell_2);
		String email = cellEmail.getStringCellValue();
		return email;
	}
	
	public String getUserPassword() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPass = row1.getCell(Constant.cell_3);
		String password = cellPass.getStringCellValue();
		return password;
	}
	
	public String getUserMobileNumber() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellMobile = row1.getCell(Constant.cell_4);
		String mobileNumber = cellMobile.getRawValue();
		return mobileNumber;
	}
	
	public String getUserPinCode() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellPin = row1.getCell(Constant.cell_5);
		String pinCode = cellPin.getRawValue(); 
		return pinCode;
	}
	
	public String getUserState() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellState = row1.getCell(Constant.cell_6);
		String state = cellState.getStringCellValue();
		return state;
	}
	
	public String getUserCity() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellCity = row1.getCell(Constant.cell_7);
		String city = cellCity.getStringCellValue();
		return city;
	}
	
	public String getUserAddress() throws IOException {

		XSSFRow row1 = getRow();
		XSSFCell cellAddress = row1.getCell(Constant.cell_8);
		String address = cellAddress.getStringCellValue(); 
		return address;
	}
	
}

