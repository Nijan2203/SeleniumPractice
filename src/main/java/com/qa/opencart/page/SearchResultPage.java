package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {

	WebDriver driver;
	ElementUtils eleutil;
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}
	
	private By searchitem = By.className("product-thumb");
	
	
	public int docountitem() {
		int count = eleutil.doFindElements(searchitem).size();
		return count;
	}

public ProductInfoPage doclickSearchitem(String value) {
	By ProductName = By.linkText(value);
	eleutil.doWaitElementLoad(ProductName, AppConstants.EXPECTED_MEDIUM_TIMEOUT).click();
	return new ProductInfoPage(driver);
}

}
