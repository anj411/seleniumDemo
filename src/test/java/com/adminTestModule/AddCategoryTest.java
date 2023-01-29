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
import com.excelTestData.CategoryExcelData;
import com.pageModule.AdminLogOutPage;
import com.pageModule.AdminLoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;
import com.util.SeleniumXpath;

public class AddCategoryTest {

	AdminExcelData adminExcelData = new AdminExcelData();
	CategoryExcelData categoryExcelData = new CategoryExcelData();

	WebDriver driver = new ChromeDriver();

	AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
	AdminLogOutPage adminLogOutPage = new AdminLogOutPage(driver);

	private Logger logger = LoggerFactory.getLogger(AddCategoryTest.class.getName());

	// When admin is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		adminLoginPage.loginToAdmin();
	}

	// When Admin add category

	@Test(priority = 1)
	public void addCategory() throws Exception {

		//Click on category section
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.catgorySectionXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		//Click on add category button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.addCategoryButtonXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		//Fill category name
		driver.findElement(SeleniumUtil.getObject(driver, "id", "name")).sendKeys(categoryExcelData.getCategoryName());

		//Select menu
		Select select = new Select(driver.findElement(By.id("menu")));
		select.selectByVisibleText("Men");

		//Add category image
		driver.findElement(SeleniumUtil.getObject(driver, "id", "categoryImage"))
				.sendKeys(categoryExcelData.getCategoryImage());
		
		//Click on submit button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.categorySubmitButton))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/admin/categories";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Category Sucessfully added");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to add");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// When admin is logout

	@AfterMethod
	public void testEnd() throws Exception {
		adminLogOutPage.logoutToAdmin();
	}

}
