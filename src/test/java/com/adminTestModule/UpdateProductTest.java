package com.adminTestModule;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.excelTestData.UpdateProductExcelData;
import com.util.Constant;
import com.util.SeleniumUtil;

public class UpdateProductTest {

	UpdateProductExcelData updateProductExcelData = new UpdateProductExcelData();
	AdminExcelData adminExcelData = new AdminExcelData();
	WebDriver driver = new ChromeDriver();
	ConfigFileReader configFileReader = new ConfigFileReader();
	
	private Logger logger = LoggerFactory.getLogger(UpdateProductTest.class.getName());

	// When admin is login
	
				@BeforeMethod
				public void adminLogin() throws Exception {
					
					System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

					driver.manage().window().maximize();
					
					driver.get("http://localhost:8080/signin");
					
					driver.findElement(SeleniumUtil.getObject(driver, "id","userName")).sendKeys(adminExcelData.getAdminEmail());
					driver.findElement(SeleniumUtil.getObject(driver, "id","userPassword")).sendKeys(adminExcelData.getAdminPassword());
					driver.findElement(SeleniumUtil.getObject(driver, "id","login")).click();

					SeleniumUtil.browser_wait(Constant.LONG_2000);
					
					String actualUrl = "http://localhost:8080/admin";
					String expectedUrl = driver.getCurrentUrl();
					
					

					if (actualUrl.equals(expectedUrl)) {
						logger.info("Admin Sucessfully login");

					} else {
						SeleniumUtil.takeScreenshot(driver);
						logger.info("Failed to login");
					}
					
					Assert.assertEquals(actualUrl, expectedUrl);
					
				}
				
				
	// When Admin Update Product
	
	@Test(priority = 5)
			public void updateProduct() throws Exception {
				
				System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		
				driver.manage().window().maximize();
		
				driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/nav/div/div[2]/ul/li[3]/a")).click();
				
				SeleniumUtil.browser_wait(Constant.LONG_2000);
				
				driver.get("http://localhost:8080/admin/products");		
				
				String url = driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/section/div/table/tbody/tr[2]/td[5]/a")).getAttribute("href");		
				
				URI uuri = new URI(url); 
				String ppath = uuri.getPath(); 
				String pid = ppath.substring(ppath.lastIndexOf('/') + 1); 
				int productId = Integer.parseInt(pid);
				
				driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/section/div/table/tbody/tr[2]/td[5]/a")).click();
				
				SeleniumUtil.browser_wait(Constant.LONG_2000);
				
				driver.get("http://localhost:8080/admin/product/update/"+productId);
				
				driver.findElement(SeleniumUtil.getObject(driver, "id","productName")).clear();
				driver.findElement(SeleniumUtil.getObject(driver, "id","price")).clear();
				driver.findElement(SeleniumUtil.getObject(driver, "id","stock")).clear();
				driver.findElement(SeleniumUtil.getObject(driver, "id","size")).clear();
				driver.findElement(SeleniumUtil.getObject(driver, "id","color")).clear();
				driver.findElement(SeleniumUtil.getObject(driver, "id","description")).clear();
				
				driver.findElement(SeleniumUtil.getObject(driver, "id","productName")).sendKeys(updateProductExcelData.getUpProductName());
				
				Select select = new Select(driver.findElement(By.id("category")));
				select.selectByVisibleText("Tops");
				
				driver.findElement(SeleniumUtil.getObject(driver, "id","price")).sendKeys(updateProductExcelData.getUpProductPrice());
				driver.findElement(SeleniumUtil.getObject(driver, "id","stock")).sendKeys(updateProductExcelData.getUpProductStock());
				driver.findElement(SeleniumUtil.getObject(driver, "id","size")).sendKeys(updateProductExcelData.getUpProductSize());
				driver.findElement(SeleniumUtil.getObject(driver, "id","color")).sendKeys(updateProductExcelData.getUpProductColor());
				driver.findElement(SeleniumUtil.getObject(driver, "id","description")).sendKeys(updateProductExcelData.getUpProductDescription());
				driver.findElement(SeleniumUtil.getObject(driver, "id","productImage")).sendKeys(updateProductExcelData.getUpProductImage());
				
				driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/section/div/div/form/div[10]/button")).click();
				
				SeleniumUtil.browser_wait(Constant.LONG_2000);	
				
				String actualUrl = "http://localhost:8080/admin/products";
				String expectedUrl = driver.getCurrentUrl();
				
				
				if (actualUrl.equals(expectedUrl)) {
					logger.info("Product Sucessfully updated");

				} else {
					SeleniumUtil.takeScreenshot(driver);
					logger.info("Failed to update");
				}
				
				Assert.assertEquals(actualUrl, expectedUrl);
				
			}	
	
	// When admin is logout
	
				@AfterMethod
				public void adminLogOut() throws Exception {
					
					System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

					driver.manage().window().maximize();
					
					driver.get("http://localhost:8080/admin");
					
					driver.findElement(SeleniumUtil.getObject(driver, "id","adminout")).click();
					
					SeleniumUtil.browser_wait(Constant.LONG_2000);
					
					String actualUrl = "http://localhost:8080/home";
					String expectedUrl = driver.getCurrentUrl();		

					if (actualUrl.equals(expectedUrl)) {
						logger.info("Admin Sucessfully logout");

					} else {
						SeleniumUtil.takeScreenshot(driver);
						logger.info("Failed to logout");
					}
					
					Assert.assertEquals(actualUrl, expectedUrl);
					
					driver.close();
					
				}
			
}
