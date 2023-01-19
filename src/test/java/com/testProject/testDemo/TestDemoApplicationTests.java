package com.testProject.testDemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.configuration.ConfigFileReader;
import com.excelTestData.AdminExcelData;
import com.excelTestData.CategoryExcelData;
import com.excelTestData.ProductExcelData;
import com.excelTestData.UpdateCategoryExcelData;
import com.excelTestData.UpdateProductExcelData;
import com.excelTestData.UserExcelData;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoponlineApplicationTests {

	UserExcelData userExcelData = new UserExcelData();
	AdminExcelData adminExcelData = new AdminExcelData();
	CategoryExcelData categoryExcelData = new CategoryExcelData();
	UpdateCategoryExcelData updateCategoryExcelData = new UpdateCategoryExcelData();
	ProductExcelData productExcelData = new ProductExcelData();
	UpdateProductExcelData updateProductExcelData = new UpdateProductExcelData();
	
	ConfigFileReader configFileReader = new ConfigFileReader();
	WebDriver driver = new ChromeDriver();
	WebElement element;
	
	// When User is register	
	@Test
	
	public void register() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();
		
		driver.get("http://localhost:8080/register");
		
		driver.findElement(By.id("userFirstName")).sendKeys(userExcelData.getFirstName());
		driver.findElement(By.id("userLastName")).sendKeys(userExcelData.getLastName());
		driver.findElement(By.id("userEmail")).sendKeys(userExcelData.getEmail());
		driver.findElement(By.id("userPassword")).sendKeys(userExcelData.getPassword());
		driver.findElement(By.id("agreement")).click();
		driver.findElement(By.xpath("/html/body/section/div/div/form/button")).click();

		Thread.sleep(2000); 

		String actualUrl = "http://localhost:8080/registerNewUser";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Successfully Registered");

		} else {
			System.out.println("Failed to register");
		}

		driver.close();
		
	}
	
	
	// When User is login	
	@Test
	public void login() throws Exception {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();
		
		driver.get("http://localhost:8080/signin");

		driver.findElement(By.id("userName")).sendKeys(userExcelData.getEmail());
		driver.findElement(By.id("userPassword")).sendKeys(userExcelData.getPassword());
		driver.findElement(By.id("login")).click();

		Thread.sleep(2000);

		String actualUrl = "http://localhost:8080/home";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Successfully login");

		} else {
			System.out.println("Failed to login");
		}
		
		driver.close();
		
	}

	
	// When Product is added to cart
	
	@Test
	public void cart() throws Exception {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		
		driver.manage().window().maximize();
		
		driver.get("http://localhost:8080/home");	
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul")).click();

		Thread.sleep(2000);
		
		driver.get(driver.getCurrentUrl());
		driver.findElement(By.xpath("/html/body/section/table/tbody/tr")).click();
		
		Thread.sleep(2000);
		
		driver.get(driver.getCurrentUrl());
		driver.findElement(By.xpath("/html/body/section/table")).click();

		Thread.sleep(2000);
		
		driver.get(driver.getCurrentUrl());
		
		String val = driver.findElement(By.id("id")).getAttribute("value");
		
		driver.findElement(By.className("size-select")).click();

		String size = null;

		if (driver.findElement(By.id("small")).isSelected()) {
			size = driver.findElement(By.id("small")).getAttribute("value");
			
		} else if (driver.findElement(By.id("medium")).isSelected()) {
			size = driver.findElement(By.id("medium")).getAttribute("value");
			
		} else if (driver.findElement(By.id("large")).isSelected()) {
			size = driver.findElement(By.id("large")).getAttribute("value");
			
		} else if (driver.findElement(By.id("x-large")).isSelected()) {
			size = driver.findElement(By.id("x-large")).getAttribute("value");
			
		} else if (driver.findElement(By.id("xx-large")).isSelected()) {
			size = driver.findElement(By.id("xx-large")).getAttribute("value");
			
		}
		
		driver.findElement(By.className("color-select")).click();
		driver.findElement(By.id("qty")).click();
		driver.findElement(By.xpath("//*[@id='detail']/form/button")).click();

		Thread.sleep(2000);

		String actualUrl = "http://localhost:8080/product/"+val+"?size="+size+"&color=on";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Successfully Added to cart");

		} else {
			System.out.println("Failed to add");
		}
		
		placeOrder(driver);
		
		driver.close();
		
	}
	
	// When Order is placed
	
	public void placeOrder(WebDriver driver) throws Exception {	
		
		driver.findElement(By.className("cart")).click();
		Thread.sleep(2000); 
		driver.get("http://localhost:8080/cart");
		 
		if (driver.findElement(By.id("log")).isDisplayed()) {		
			
			Thread.sleep(2000);
			
			driver.get("http://localhost:8080/signin");
			driver.findElement(By.id("userName")).sendKeys(userExcelData.getEmail());
			driver.findElement(By.id("userPassword")).sendKeys(userExcelData.getPassword());
			driver.findElement(By.id("login")).click();
			
			Thread.sleep(2000);
			
			driver.get("http://localhost:8080/home");
			driver.findElement(By.className("cart")).click();
			
			Thread.sleep(2000); 
			driver.get("http://localhost:8080/cart");		
		} 
		
		driver.findElement(By.id("place")).click();
		Thread.sleep(2000);
		
		saveAddress(driver);				
				
	}
	
	// When Address is saved
	
	public void saveAddress(WebDriver driver) throws InterruptedException, IOException {
		
		driver.get("http://localhost:8080/placeorder");

		String name = driver.findElement(By.id("name")).getAttribute("value");
		if (name == null || name == "") {
			driver.findElement(By.id("name")).sendKeys(userExcelData.getFirstName() + " " + userExcelData.getLastName());
		}
			
		String mobile = driver.findElement(By.id("number")).getAttribute("value");
		if (mobile == null || mobile == "") {
			driver.findElement(By.id("number")).sendKeys(userExcelData.getMobileNumber());
		}
			
		String pinCode = driver.findElement(By.id("code")).getAttribute("value");
		if (pinCode == null || pinCode == "") {
			driver.findElement(By.id("code")).sendKeys(userExcelData.getPinCode());
		}
		
		String city = driver.findElement(By.id("city")).getAttribute("value");
		if (city == null || city == "") {
			driver.findElement(By.id("city")).sendKeys(userExcelData.getCity());
		}
		
		String address = driver.findElement(By.id("address")).getAttribute("value");
		if (address == null || address == "") {
			driver.findElement(By.id("address")).sendKeys(userExcelData.getAddress());
		}
			
		driver.findElement(By.id("order")).click();
			
		Thread.sleep(2000);
			
		String actualUrl = "http://localhost:8080/payment";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Address successfully saved");

		} else {
			System.out.println("Failed to save");
		}
		
		confirmOrder(driver);
			
	}
	
	
	// When Order is confirmed
	
	public void confirmOrder(WebDriver driver) throws InterruptedException {
		
		driver.findElement(By.id("confirm")).click();
		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/confirmorder";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Order Sucessfully Confirmed");

		} else {
			System.out.println("Failed to order");
		}
		
	}
	
	
	// When user is Admin
	
	@Test
	public void adminLogin() throws InterruptedException, IOException, URISyntaxException {
		
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());

		driver.manage().window().maximize();
		
		driver.get("http://localhost:8080/signin");
		
		driver.findElement(By.id("userName")).sendKeys(adminExcelData.getEmail());
		driver.findElement(By.id("userPassword")).sendKeys(adminExcelData.getPassword());
		driver.findElement(By.id("login")).click();

		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/admin";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Admin Sucessfully login");

		} else {
			System.out.println("Failed to login");
		}
		
		//addCategory(driver);
		//updateCategory(driver);
		//addProduct(driver);
		//updateProduct(driver);
		deleteProduct(driver);
		driver.close();
		
	}
	
	
	// When Admin add category

	public void addCategory(WebDriver driver) throws InterruptedException, IOException {
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/categories");
		driver.findElement(By.xpath("/html/body/section/div/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/categories/add");
		driver.findElement(By.id("name")).sendKeys(categoryExcelData.getCategoryName());
		
		Select select = new Select(driver.findElement(By.id("menu")));
		select.selectByVisibleText("Men");
		
		driver.findElement(By.id("categoryImage")).sendKeys(categoryExcelData.getCategoryImage());
		driver.findElement(By.xpath("/html/body/section/div/div/div/div/form/button")).click();
		
		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/admin/categories";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Category Sucessfully added");

		} else {
			System.out.println("Failed to add");
		}
		
	}
	
	
	// When Admin update category
	
	public void updateCategory(WebDriver driver) throws InterruptedException, IOException, URISyntaxException {
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/categories");
		
		String url = driver.findElement(By.xpath("/html/body/section/div/table/tbody/tr[1]/td[2]/a")).getAttribute("href");		
		URI uri = new URI(url);
		String path = uri.getPath();
		String id = path.substring(path.lastIndexOf('/') + 1);
		int categoryId = Integer.parseInt(id);		
		
		driver.findElement(By.xpath("/html/body/section/div/table/tbody/tr[1]/td[2]/a")).click();

		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/categories/update/"+categoryId);
		driver.findElement(By.id("name")).clear();	
		driver.findElement(By.id("name")).sendKeys(updateCategoryExcelData.getUpCategoryName());
		
		Select select = new Select(driver.findElement(By.id("menu")));
		select.selectByVisibleText("Women");
 
		driver.findElement(By.id("categoryImage")).sendKeys(updateCategoryExcelData.getUpCategoryImage());
		driver.findElement(By.xpath("/html/body/section/div/div/div/div/form/button")).click();
		
		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/admin/categories";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Category Sucessfully updated");

		} else {
			System.out.println("Failed to update");
		}
	}
	
	
	// When Admin add Product 
	
	public void addProduct(WebDriver driver) throws InterruptedException, IOException {
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[3]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/products");
		driver.findElement(By.xpath("/html/body/section/div/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/products/add");
		
		driver.findElement(By.id("productName")).sendKeys(productExcelData.getProductName());
		
		Select select = new Select(driver.findElement(By.id("category")));
		select.selectByVisibleText("Ethnic Wear");
		
		driver.findElement(By.id("price")).clear();		
		driver.findElement(By.id("price")).sendKeys(productExcelData.getProductPrice());
		
		driver.findElement(By.id("stock")).clear();
		driver.findElement(By.id("stock")).sendKeys(productExcelData.getProductStock());
		
		driver.findElement(By.id("size")).sendKeys(productExcelData.getProductSize());
		driver.findElement(By.id("color")).sendKeys(productExcelData.getProductColor());
		driver.findElement(By.id("description")).sendKeys(productExcelData.getProductDescription());
		driver.findElement(By.id("productImage")).sendKeys(productExcelData.getProductImage());
				
		driver.findElement(By.xpath("/html/body/section/div/div/form/div[10]/button")).click();
		
		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/admin/products";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Product Sucessfully added");

		} else {
			System.out.println("Failed to add");
		}
		
		
	}
	
	
	// When Admin Update Product
	
	public void updateProduct(WebDriver driver) throws InterruptedException, IOException, URISyntaxException {
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[3]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/products");		
		
		String url = driver.findElement(By.xpath("/html/body/section/div/table/tbody/tr[2]/td[5]/a")).getAttribute("href");		
		
		URI uuri = new URI(url); 
		String ppath = uuri.getPath(); 
		String pid = ppath.substring(ppath.lastIndexOf('/') + 1); 
		int productId = Integer.parseInt(pid);
		
		driver.findElement(By.xpath("/html/body/section/div/table/tbody/tr[2]/td[5]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/product/update/"+productId);
		
		driver.findElement(By.id("productName")).clear();
		driver.findElement(By.id("price")).clear();
		driver.findElement(By.id("stock")).clear();
		driver.findElement(By.id("size")).clear();
		driver.findElement(By.id("color")).clear();
		driver.findElement(By.id("description")).clear();
		
		driver.findElement(By.id("productName")).sendKeys(updateProductExcelData.getUpProductName());
		
		Select select = new Select(driver.findElement(By.id("category")));
		select.selectByVisibleText("Tops");
		
		driver.findElement(By.id("price")).sendKeys(updateProductExcelData.getUpProductPrice());
		driver.findElement(By.id("stock")).sendKeys(updateProductExcelData.getUpProductStock());
		driver.findElement(By.id("size")).sendKeys(updateProductExcelData.getUpProductSize());
		driver.findElement(By.id("color")).sendKeys(updateProductExcelData.getUpProductColor());
		driver.findElement(By.id("description")).sendKeys(updateProductExcelData.getUpProductDescription());
		driver.findElement(By.id("productImage")).sendKeys(updateProductExcelData.getUpProductImage());
		
		driver.findElement(By.xpath("/html/body/section/div/div/form/div[10]/button")).click();
		
		Thread.sleep(2000);	
		
		String actualUrl = "http://localhost:8080/admin/products";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Product Sucessfully updated");

		} else {
			System.out.println("Failed to update");
		}
		
	}
	
	
	// When Admin Delete Product
	
	public void deleteProduct(WebDriver driver) throws InterruptedException, IOException {
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[3]/a")).click();
		
		Thread.sleep(2000);
		
		driver.get("http://localhost:8080/admin/products");
		driver.findElement(By.xpath("/html/body/section/div/table/tbody/tr[10]/td[4]/button")).click();
	
		Thread.sleep(2000);
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		
		String actualUrl = "http://localhost:8080/admin/products";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualUrl, expectedUrl);

		if (actualUrl.equals(expectedUrl)) {
			System.out.println("Product Sucessfully deleted");

		} else {
			System.out.println("Failed to delete");
		}
		
	}
	
}
