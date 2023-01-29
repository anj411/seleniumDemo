package com.excelTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Constant;

public class ProductExcelData {

	public XSSFRow getRow() throws IOException {
		
		File file =    new File("C:\\Program Files\\Git\\testDemo\\ProductData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = wb.getSheetAt(Constant.cell_0);
		
		XSSFRow row1 = sheet.getRow(Constant.cell_1);
		
		return row1;
	}
	
	public String getProductName() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellName = row1.getCell(Constant.cell_0);		
		String productName = cellName.getStringCellValue(); 
		return productName;
		
	}
	
	public String getProductImage() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellImage = row1.getCell(Constant.cell_1);
		String productImage = cellImage.getStringCellValue();  
		return productImage;
		
	}
	
	public String getProductPrice() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellPrice = row1.getCell(Constant.cell_2);
		String productPrice = cellPrice.getRawValue();  
		return productPrice;
		
	}
	
	public String getProductCode() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellCode = row1.getCell(Constant.cell_3);
		String productCode = cellCode.getRawValue();  
		return productCode;
		
	}
	
	public String getProductDescription() throws IOException {
		
		XSSFRow row1 = getRow();
		XSSFCell cellDescription = row1.getCell(Constant.cell_4);
		String productDescription = cellDescription.getStringCellValue();  
		return productDescription;
		
	}
	
}
