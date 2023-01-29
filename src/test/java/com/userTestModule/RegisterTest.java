package com.userTestModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.UserExcelData;
import com.util.Constant;
import com.util.SeleniumUtil;

public class RegisterTest {

	ConfigFileReader configFileReader = new ConfigFileReader();

	WebDriver driver = new ChromeDriver();

	UserExcelData userExcelData = new UserExcelData();

	private Logger logger = LoggerFactory.getLogger(RegisterTest.class.getName());

	// Set user firstName in textbox

	public void setUserFirstName(String strUserFirstName) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userFirstName")).sendKeys(strUserFirstName);
	}

	// Set user lastName in textbox

	public void setUserLastName(String strUserLastName) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userLastName")).sendKeys(strUserLastName);
	}

	// Set user email in textbox

	public void setUserEmail(String strUserEmail) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userEmail")).sendKeys(strUserEmail);
	}

	// Set user password in textbox

	public void setUserPassword(String strUserPassword) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userPassword")).sendKeys(strUserPassword);
	}

	// Set address in textbox

	public void setAddress(String strAddress) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userAddress")).sendKeys(strAddress);
	}

	// Click on terms and condition checkbox

	public void clickCondition() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "agreement")).click();
	}

	// Click on submit button

	public void submit() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", "/html/body/section/div/div/form/button")).click();
	}

	
	// When User is register

	@Test
	public void registerToUser() throws Exception {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();

		driver.get("http://localhost:8080/register");

		// Fill user firstName
		this.setUserFirstName(userExcelData.getUserFirstName());

		// Fill user lastName
		this.setUserLastName(userExcelData.getUserLastName());

		// Fill user email
		this.setUserEmail(userExcelData.getUserEmail());

		// Fill password
		this.setUserPassword(userExcelData.getUserPassword());

		// Fill address
		this.setAddress(userExcelData.getUserAddress());

		// Click terms and condition checkbox
		this.clickCondition();

		// Click Login button
		this.submit();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/registerNewUser";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Successfully Registered");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to register");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

		driver.close();

	}

}
