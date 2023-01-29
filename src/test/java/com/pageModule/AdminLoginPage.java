package com.pageModule;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.excelTestData.UserExcelData;
import com.util.Constant;
import com.util.SeleniumUtil;

public class AdminLoginPage {

	ConfigFileReader configFileReader = new ConfigFileReader();

	WebDriver driver;

	AdminExcelData adminExcelData = new AdminExcelData();

	private Logger logger = LoggerFactory.getLogger(AdminLoginPage.class.getName());

	public AdminLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Set admin email in textbox

	public void setAdminEmail(String strAdminEmail) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userName")).sendKeys(strAdminEmail);
	}

	// Set password in textbox

	public void setPassword(String strPassword) throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "userPassword")).sendKeys(strPassword);
	}

	// Click on login button

	public void clickLogin() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "login")).click();
	}

	public void loginToAdmin() throws Exception {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();

		driver.get("http://localhost:8080/signin");

		// Fill user name
		this.setAdminEmail(adminExcelData.getAdminEmail());

		// Fill password
		this.setPassword(adminExcelData.getAdminPassword());

		// Click Login button
		this.clickLogin();

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

}
