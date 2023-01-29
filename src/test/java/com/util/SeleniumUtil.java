package com.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.testng.Assert;

import com.excelTestData.AdminExcelData;
import com.excelTestData.UserExcelData;

public class SeleniumUtil {

	private static Logger logger = LoggerFactory.getLogger(SeleniumUtil.class.getName());

	public static void browser_wait(Long waitingTime) {

		try {
			Thread.sleep(waitingTime);

		} catch (InterruptedException ie) {
			logger.error("Browser_wait.", ie.getMessage());
		}

	}

	public static By getObject(WebDriver driver, String locatorType, String locatorValue) throws Exception {

		// Find by xpath

		if (locatorType.equalsIgnoreCase("xpath")) {
			return By.xpath(locatorValue);
		}

		// Find by ID

		else if (locatorType.equalsIgnoreCase("id")) {
			return By.id(locatorValue);
		}

		// find by class

		else if (locatorType.equalsIgnoreCase("className")) {
			return By.className(locatorValue);
		}

		// find by name

		else if (locatorType.equalsIgnoreCase("name")) {
			return By.name(locatorValue);
		}

		// Find by css

		else if (locatorType.equalsIgnoreCase("css")) {
			return By.cssSelector(locatorValue);
		}

		// find by link

		else if (locatorType.equalsIgnoreCase("linkText")) {
			return By.linkText(locatorValue);
		}

		// find by partial link

		else if (locatorType.equalsIgnoreCase("partialLink")) {
			return By.partialLinkText(locatorValue);
		}

		// find by tag name

		else if (locatorType.equalsIgnoreCase("tagName")) {
			return By.tagName(locatorValue);
		}

		else {
			throw new Exception("Wrong locator type: " + locatorType);
		}

	}

	public static void takeScreenshot(WebDriver driver) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path;
		path = "C:\\Program Files\\Git\\testDemo\\test-output\\" + scrFile.getName();
		FileCopyUtils.copy(scrFile, new File(path));

	}

}
