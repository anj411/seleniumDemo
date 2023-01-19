package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ProductExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\shoponlineData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row1 = sheet.getRow(11);
		
		return row1;
	}
	
	public String getProductName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellName = row1.getCell(0);		
		String productName = cellName.getStringCellValue(); 
		return productName;
		
	}
	
	public String getProductImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellImage = row1.getCell(1);
		String productImage = cellImage.getStringCellValue();  
		return productImage;
		
	}
	
	public String getProductPrice() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPrice = row1.getCell(2);
		String productPrice = cellPrice.getRawValue();  
		return productPrice;
		
	}
	
	public String getProductStock() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellStock = row1.getCell(3);
		String productStock = cellStock.getRawValue();  
		return productStock;
		
	}
	
	public String getProductSize() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellSize = row1.getCell(4);
		String productSize = cellSize.getStringCellValue();  
		return productSize;
		
	}
	
	public String getProductColor() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellColor = row1.getCell(5);
		String productColor = cellColor.getStringCellValue();  
		return productColor;
		
	}
	
	public String getProductDescription() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellDescription = row1.getCell(6);
		String productDescription = cellDescription.getStringCellValue();  
		return productDescription;
		
	}
	
}
