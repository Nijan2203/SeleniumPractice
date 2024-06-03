package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.page.AccountPage;
import com.qa.opencart.page.LoginPage;
import com.qa.opencart.page.ProductInfoPage;
import com.qa.opencart.page.SearchResultPage;

public class BaseTest {
	DriverFactory df;
	public WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage prdInfoPage;
	protected SoftAssert softAssert;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initBrowser(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	
	}

//	@AfterTest
//	public void teardown() {
//		driver.quit();
//	}

}
