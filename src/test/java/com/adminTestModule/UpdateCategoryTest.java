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
import com.excelTestData.UpdateCategoryExcelData;
import com.pageModule.AdminLogOutPage;
import com.pageModule.AdminLoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;
import com.util.SeleniumXpath;

public class UpdateCategoryTest {

	UpdateCategoryExcelData updateCategoryExcelData = new UpdateCategoryExcelData();
	AdminExcelData adminExcelData = new AdminExcelData();

	WebDriver driver = new ChromeDriver();

	AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
	AdminLogOutPage adminLogOutPage = new AdminLogOutPage(driver);

	private Logger logger = LoggerFactory.getLogger(UpdateCategoryTest.class.getName());

	// When admin is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		adminLoginPage.loginToAdmin();
	}

	
	// When Admin update category

	@Test(priority = 2)
	public void updateCategory() throws Exception {

		// Click on category section
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.catgorySectionXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Click on update button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.updateCategoryButtonXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		// Clear category name
		driver.findElement(SeleniumUtil.getObject(driver, "id", "name")).clear();

		// Fill category name
		driver.findElement(SeleniumUtil.getObject(driver, "id", "name"))
				.sendKeys(updateCategoryExcelData.getUpCategoryName());

		// Select menu
		Select select = new Select(driver.findElement(By.id("menu")));
		select.selectByVisibleText("Men");

		// Update category image
		driver.findElement(SeleniumUtil.getObject(driver, "id", "categoryImage"))
				.sendKeys(updateCategoryExcelData.getUpCategoryImage());

		// Click on submit button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.categorySubmitButton)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/admin/categories";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Category Sucessfully updated");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to update");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

	
	// When admin is logout

	@AfterMethod
	public void testEnd() throws Exception {
		adminLogOutPage.logoutToAdmin();
	}

}
