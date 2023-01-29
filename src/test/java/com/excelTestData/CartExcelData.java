package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Constant;

public class CartExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\CartData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(Constant.cell_0);
		
		XSSFRow row1 = sheet.getRow(Constant.cell_1);
		
		return row1;
	}
	
	public String getProductSize() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellSize = row1.getCell(Constant.cell_0);		
		String size = cellSize.getStringCellValue(); 
		return size;
	}
	
	public String getProductQty() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellQty = row1.getCell(Constant.cell_1);
		String qty = cellQty.getRawValue();  
		return qty;
	}

	
}
