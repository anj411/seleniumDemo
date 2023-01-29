package com.adminTestModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.pageModule.AdminLogOutPage;
import com.pageModule.AdminLoginPage;
import com.util.Constant;
import com.util.SeleniumUtil;
import com.util.SeleniumXpath;

public class DeleteCategoryTest {

	WebDriver driver = new ChromeDriver();

	AdminExcelData adminExcelData = new AdminExcelData();

	AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
	AdminLogOutPage adminLogOutPage = new AdminLogOutPage(driver);

	private Logger logger = LoggerFactory.getLogger(DeleteCategoryTest.class.getName());

	// When admin is login

	@BeforeMethod
	public void testInitiallize() throws Exception {
		adminLoginPage.loginToAdmin();
	}

	
	// When Admin delete category

	@Test(priority = 3)
	public void deleteCategory() throws Exception {
		
		//Click on category section
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.catgorySectionXpath)).click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		//click on delete button
		driver.findElement(SeleniumUtil.getObject(driver, "xpath", SeleniumXpath.deleteCategoryButtonXpath))
				.click();

		SeleniumUtil.browser_wait(Constant.LONG_2000);

		String actualUrl = "http://localhost:8080/admin/categories";
		String expectedUrl = driver.getCurrentUrl();

		if (actualUrl.equals(expectedUrl)) {
			logger.info("Category Sucessfully deleted");

		} else {
			SeleniumUtil.takeScreenshot(driver);
			logger.info("Failed to delete");
		}

		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// When admin is logout

	@AfterMethod
	public void testEnd() throws Exception {
		adminLogOutPage.logoutToAdmin();
	}

}
