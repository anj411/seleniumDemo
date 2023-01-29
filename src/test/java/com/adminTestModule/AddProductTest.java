package com.adminTestModule;

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
import com.excelTestData.ProductExcelData;
import com.pageModule.AdminLogOutPage;
import com.pageModule.AdminLoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;
import com.util.SeleniumXpath;

public class AddProductTest {

	ProductExcelData productExcelData = new ProductExcelData();
	AdminExcelData adminExcelData = new AdminExcelData();

	WebDriver driver = new ChromeDriver();

	AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
	AdminLogOutPage adminLogOutPage = new AdminLogOutPage(driver);

	private Logger logger = LoggerFactory.getLogger(AddProductTest.class.getName());

	// When admin is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		adminLoginPage.loginToAdmin();
	}

	// When Admin add Product

	@Test(priority = 4)
	public void addProduct() throws Exception {

		//Click on product section
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.productSectionXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		//Click on add product button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.addProductButtonXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		//Fill product name
		driver.findElement(SeleniumUtil.getObject(driver, "id", "productName"))
				.sendKeys(productExcelData.getProductName());

		//Select category
		Select select = new Select(driver.findElement(By.id("category")));
		select.selectByVisibleText("Jackets");

		//Clear product price
		driver.findElement(SeleniumUtil.getObject(driver, "id", "price")).clear();
		
		//Fill product price
		driver.findElement(SeleniumUtil.getObject(driver, "id", "price")).sendKeys(productExcelData.getProductPrice());

		//Fill product code
		driver.findElement(SeleniumUtil.getObject(driver, "id", "stock")).sendKeys();
		
		//Fill product description
		driver.findElement(SeleniumUtil.getObject(driver, "id", "description"))
				.sendKeys(productExcelData.getProductDescription());
		
		//Add product image
		driver.findElement(SeleniumUtil.getObject(driver, "id", "productImage"))
				.sendKeys(productExcelData.getProductImage());

		//Click on add product button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", "/html/body/section/div/div/form/div[10]/button"))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/admin/products";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Product Sucessfully added");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to add");
		}

	}

	// When admin is logout

	@AfterMethod
	public void testEnd() throws Exception {
		adminLogOutPage.logoutToAdmin();
	}

}
