package com.pageModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.configuration.ConfigFileReader;
import com.excelTestData.UserExcelData;
import com.util.Constant;
import com.util.SeleniumUtil;

public class LoginPage {

	ConfigFileReader configFileReader = new ConfigFileReader();

	WebDriver driver;

	UserExcelData userExcelData = new UserExcelData();

	private Logger logger = LoggerFactory.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Set user name in textbox

	public void setUserName(String strUserName) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userName")).sendKeys(strUserName);
	}

	// Set password in password textbox

	public void setPassword(String strPassword) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userPassword")).sendKeys(strPassword);
	}

	// Click on login button

	public void clickLogin() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "login")).click();
	}

	public void loginToUser() throws Exception {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();

		driver.get("http://localhost:8080/signin");

		// Fill user name
		this.setUserName(userExcelData.getUserEmail());

		// Fill password
		this.setPassword(userExcelData.getUserPassword());

		// Click Login button
		this.clickLogin();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/home";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Successfully login");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to login");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

}
