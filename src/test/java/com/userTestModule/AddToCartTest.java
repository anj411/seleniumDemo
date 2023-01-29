package com.userTestModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.excelTestData.CartExcelData;
import com.excelTestData.UserExcelData;
import com.pageModule.LogOutPage;
import com.pageModule.LoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;
import com.util.SeleniumXpath;

public class AddToCartTest {

	WebDriver driver = new ChromeDriver();

	UserExcelData userExcelData = new UserExcelData();
	AdminExcelData adminExcelData = new AdminExcelData();
	CartExcelData cartExcelData = new CartExcelData();

	LoginPage loginPage = new LoginPage(driver);
	LogOutPage logOutPage = new LogOutPage(driver);

	private Logger logger = LoggerFactory.getLogger(AddToCartTest.class.getName());

	// user is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		loginPage.loginToUser();
	}

	
	// When Product is added to cart

	@Test(priority = 1)
	public void cart() throws Exception {

		// Select Menu
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.menuXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Select Category
		driver.findElement(
				SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.categoryXpath))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Select Product
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.productXpath))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Get product id
		String val = driver.findElement(SeleniumUtil.getObject(driver, "id", "id")).getAttribute("value");

		// Select size
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.sizeXpath))
				.click();

		String size = null;

		// Get size
		size = cartExcelData.getProductSize();

		// Add quantity
		driver.findElement(SeleniumUtil.getObject(driver, "id", "qty")).sendKeys(cartExcelData.getProductQty());

		// Click on add to cart button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.addToCartButtonXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/product/" + val + "?size=" + size + "&color=on";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Successfully Added to cart");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to add");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

	
	// When user is logout

	@AfterMethod
	public void testEnd() throws Exception {
		logOutPage.logoutToUser();
	}

}
