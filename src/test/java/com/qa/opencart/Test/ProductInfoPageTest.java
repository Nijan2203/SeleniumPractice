package com.qa.opencart.Test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	
	@BeforeClass
	public void dologinSetup() {
		accPage = loginPage.doLoginCheck(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getsearchdata() {
		Object[][] searchdat =  ExcelUtil.getExcelData("SearchData");
		return searchdat;
		}

	@Test(priority = 1, dataProvider = "getsearchdata")
	public void getImageCountTest(String searchkey, String ProductName) {
	searchPage = accPage.doSearch(searchkey);
	prdInfoPage =  searchPage.doclickSearchitem(ProductName);
	int igcount = prdInfoPage.getImageCount();
	Assert.assertTrue(igcount>0);
	}
	
	@Test(priority = 2)
	public void getproductinfoTest() {
		searchPage = accPage.doSearch("Macbook");
		prdInfoPage = searchPage.doclickSearchitem("MacBook Pro");
		Map<String, String> actprdMap = prdInfoPage.getproductinfo();
		softAssert.assertEquals(actprdMap.get("Brand"), "Apple");
		softAssert.assertEquals(actprdMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actprdMap.get("Reward Points"), "800");
		softAssert.assertEquals(actprdMap.get("Availability"), "In Stock");
		
		softAssert.assertAll();
	}

	@Test(priority = 3,  dataProvider = "getsearchdata")
	public void doAddToCartTest(String searchkey, String ProductName) {
		searchPage = accPage.doSearch(searchkey);
		prdInfoPage =  searchPage.doclickSearchitem(ProductName);
	String msg = prdInfoPage.doAddToCart();
	Assert.assertEquals(msg, "Success: You have added "+ ProductName +" to your shopping cart!");
	}
	
}
