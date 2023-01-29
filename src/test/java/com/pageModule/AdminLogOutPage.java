package com.pageModule;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.util.Constant;
import com.util.SeleniumUtil;

public class AdminLogOutPage {

	WebDriver driver;

	private Logger logger = LoggerFactory.getLogger(AdminLogOutPage.class.getName());

	public AdminLogOutPage(WebDriver driver) {
		this.driver = driver;
	}

	// click on logout button

	public void clickLogOut() throws Exception {
		driver.findElement(SeleniumUtil.getObject(driver, "id","adminout")).click();
	}

	// When user is logout

	public void logoutToAdmin() throws Exception {

		driver.get("http://localhost:8080/admin");

		// Click Logout button
		this.clickLogOut();

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
