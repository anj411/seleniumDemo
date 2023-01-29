package com.userTestModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.UserExcelData;
import com.pageModule.LogOutPage;
import com.pageModule.LoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;

public class PlaceOrderTest {

	UserExcelData userExcelData = new UserExcelData();

	WebDriver driver = new ChromeDriver();

	private Logger logger = LoggerFactory.getLogger(PlaceOrderTest.class.getName());

	LoginPage loginPage = new LoginPage(driver);
	LogOutPage logOutPage = new LogOutPage(driver);

	// user is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		loginPage.loginToUser();
	}

	
	// When Order is placed

	@Test(priority = 2)
	public void placeOrder() throws Exception {

		// Click on cart button
		driver.findElement(SeleniumUtil.getObject(driver, "className", "cart")).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Check place order button is displayed or not if displayed then Click on place order button
		if (driver.findElement(SeleniumUtil.getObject(driver, "id", "place")).isDisplayed()) {
			driver.findElement(SeleniumUtil.getObject(driver, "id", "place")).click();
		}

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/placeorder";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Successfully Order Placed");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to placed");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

	
	// When user is logout

	@AfterMethod
	public void testEnd() throws Exception {
		logOutPage.logoutToUser();
	}

}
