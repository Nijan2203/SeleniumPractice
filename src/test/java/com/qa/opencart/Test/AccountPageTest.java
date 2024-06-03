package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void doLoginpage() {
		accPage = loginPage.doLoginCheck(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void doAccountPageTitleTest() {
		String title = accPage.doAccountPageTitle();
		Assert.assertEquals(title, AppConstants.EXPECTED_ACCOUNT_PAGE_TITLE);
	}

	
	@DataProvider
	public Object[][] searchKeyData() {
		return new Object[][] {
			{"Macbook"},
			{"iphone"}
		};
	}
	
	@Test(priority = 2,dataProvider = "searchKeyData")
	public void doAccountSearchTest(String searchkey) {
		accPage.doSearch(searchkey);
	}
	
}
