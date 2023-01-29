package com.adminTestModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.util.Constant;
import com.util.SeleniumUtil;

public class DeleteProductTest {

	ConfigFileReader configFileReader = new ConfigFileReader();
	WebDriver driver = new ChromeDriver();
	AdminExcelData adminExcelData = new AdminExcelData();
	private Logger logger = LoggerFactory.getLogger(DeleteProductTest.class.getName());

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
	
	
	// When Admin delete product
	
	@Test(priority = 6)
	public void deleteProduct() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		
		driver.manage().window().maximize();
		
		driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/nav/div/div[2]/ul/li[3]/a")).click();
		
		SeleniumUtil.browser_wait(Constant.LONG_2000);
		
		driver.get("http://localhost:8080/admin/products");
		driver.findElement(SeleniumUtil.getObject(driver, "xpath","/html/body/section/div/table/tbody/tr[10]/td[4]/button")).click();
	
		SeleniumUtil.browser_wait(Constant.LONG_2000);
		
		driver.switchTo().alert().accept();
		
		SeleniumUtil.browser_wait(Constant.LONG_2000);
		
		String actualUrl = "http://localhost:8080/admin/products";
		String expectedUrl = driver.getCurrentUrl();
		
		

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Product Sucessfully deleted");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to delete");
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
