package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLoginCheck(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getSearchData() {

		return new Object[][] { { "Macbook", "MacBook Air", 3 } };
	}

	@Test(priority = 1, dataProvider = "getSearchData")
	public void DoSearchItemCountTest(String searchKey, String Productname, int imagecount) {
		searchPage = accPage.doSearch(searchKey);
		int count = searchPage.docountitem();
		Assert.assertTrue(count > 0);
		;
	}

	@Test(priority = 2, dataProvider = "getSearchData")
	public void doSearchItemClickTest(String searchKey, String Productname, int imagecount) {
		searchPage = accPage.doSearch(searchKey);
		prdInfoPage = searchPage.doclickSearchitem(Productname);
		String prgHead = prdInfoPage.getProductHeader();
		Assert.assertEquals(Productname, prgHead);
	}
}
