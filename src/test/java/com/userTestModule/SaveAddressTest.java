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
import com.util.SeleniumXpath;

public class SaveAddressTest {

	UserExcelData userExcelData = new UserExcelData();

	WebDriver driver = new ChromeDriver();

	private Logger logger = LoggerFactory.getLogger(SaveAddressTest.class.getName());

	LoginPage loginPage = new LoginPage(driver);
	LogOutPage logOutPage = new LogOutPage(driver);

	// user is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		loginPage.loginToUser();
	}

	
	// When Address is saved

	@Test
	public void saveAddress() throws Exception {

		driver.get("http://localhost:8080/placeorder");

		// Get name
		String name = driver.findElement(SeleniumUtil.getObject(driver, "id", "name")).getAttribute("value");

		// If name is blank then fill name
		if (name == null || name == "") {
			driver.findElement(SeleniumUtil.getObject(driver, "id", "name"))
					.sendKeys(userExcelData.getUserFirstName() + " " + userExcelData.getUserLastName());
		}

		// Get mobile number
		String mobile = driver.findElement(SeleniumUtil.getObject(driver, "id", "number")).getAttribute("value");

		// If mobile number is blank then fill mobile number
		if (mobile == null || mobile == "") {
			driver.findElement(SeleniumUtil.getObject(driver, "id", "number"))
					.sendKeys(userExcelData.getUserMobileNumber());
		}

		// Get pincode
		String pinCode = driver.findElement(SeleniumUtil.getObject(driver, "id", "code")).getAttribute("value");

		// If pincode is blank then fill pincode
		if (pinCode == null || pinCode == "") {
			driver.findElement(SeleniumUtil.getObject(driver, "id", "code")).sendKeys(userExcelData.getUserPinCode());
		}

		// Fill state
		driver.findElement(SeleniumUtil.getObject(driver, "id", "state")).sendKeys(userExcelData.getUserState());

		// Fill city
		driver.findElement(SeleniumUtil.getObject(driver, "id", "city")).sendKeys(userExcelData.getUserCity());

		// Get address
		String address = driver.findElement(SeleniumUtil.getObject(driver, "id", "address")).getAttribute("value");

		// If address is blank then fill address
		if (address == null || address == "") {
			driver.findElement(SeleniumUtil.getObject(driver, "id", "address"))
					.sendKeys(userExcelData.getUserAddress());
		}

		// Click on order now button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.orderNowButtonXpath))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/payment";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Address successfully saved");

		} else {
			logger.info("Failed to save");
		}

	}

	
	// When user is logout

	@AfterMethod
	public void testEnd() throws Exception {
		logOutPage.logoutToUser();
	}

}
