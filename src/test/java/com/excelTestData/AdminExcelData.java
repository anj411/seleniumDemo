package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Constant;

public class AdminExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\AdminData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(Constant.cell_0);
		
		XSSFRow row1 = sheet.getRow(Constant.cell_1);
		
		return row1;
	}
	
	public String getAdminEmail() throws IOException {
	
		XSSFRow row1 = getRow();
		XSSFCell cellEmail = row1.getCell(Constant.cell_0);
		String email = cellEmail.getStringCellValue();
		return email;
	}
	
	public String getAdminPassword() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPass = row1.getCell(Constant.cell_1);
		String password = cellPass.getStringCellValue();
		return password;
	}
	
}
