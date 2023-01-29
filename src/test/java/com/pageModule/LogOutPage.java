package com.pageModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.configuration.ConfigFileReader;
import com.util.Constant;
import com.util.SeleniumUtil;

public class LogOutPage {

	WebDriver driver;

	private Logger logger = LoggerFactory.getLogger(LogOutPage.class.getName());

	public LogOutPage(WebDriver driver) {
		this.driver = driver;
	}

	// click on logout button

	public void clickLogOut() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id", "out")).click();
	}

	// When user is logout

	public void logoutToUser() throws Exception {

		driver.get("http://localhost:8080/home");

		// Click Logout button
		this.clickLogOut();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/home";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Successfully log out");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to log out");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

		driver.close();

	}

}
