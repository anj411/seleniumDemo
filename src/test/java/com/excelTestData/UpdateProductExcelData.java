package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Constant;

public class UpdateProductExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\ProductUpdatedData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(Constant.cell_0);
		
		XSSFRow row1 = sheet.getRow(Constant.cell_1);
		
		return row1;
		
	}
	
	public String getUpProductName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellName = row1.getCell(Constant.cell_0);		
		String upProductName = cellName.getStringCellValue(); 
		return upProductName;
		
	}
	
	public String getUpProductImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellImage = row1.getCell(Constant.cell_1);
		String upProductImage = cellImage.getStringCellValue();  
		return upProductImage;
		
	}
	
	public String getUpProductPrice() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPrice = row1.getCell(Constant.cell_2);
		String upProductPrice = cellPrice.getRawValue();  
		return upProductPrice;
		
	}
	
	public String getUpProductStock() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellStock = row1.getCell(Constant.cell_3);
		String upProductStock = cellStock.getRawValue();  
		return upProductStock;
		
	}
	
	public String getUpProductSize() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellSize = row1.getCell(Constant.cell_4);
		String upProductSize = cellSize.getStringCellValue();  
		return upProductSize;
		
	}
	
	public String getUpProductColor() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellColor = row1.getCell(Constant.cell_5);
		String upProductColor = cellColor.getStringCellValue();  
		return upProductColor;
		
	}
	
	public String getUpProductDescription() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellDescription = row1.getCell(Constant.cell_6);
		String upProductDescription = cellDescription.getStringCellValue();  
		return upProductDescription;
		
	}
	
}
