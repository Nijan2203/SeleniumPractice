package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {
WebDriver driver;
	ElementUtils eleutil;
	
	public AccountPage(WebDriver driver) {
		 this.driver = driver;
	eleutil= new ElementUtils(driver);
	
	}
	
	
	private By searchbar = By.name("search");
	private By searchbtn = By.xpath("//button[@class='btn btn-default btn-lg']");
	private By logoutbtn = By.linkText("Logout");

	public String doAccountPageTitle() {
		String title = eleutil.doGetTitle();
		return title;
	}

	
	public Boolean doLogoutBtnCheck() {
		Boolean lobtn = eleutil.dodisplaycheck(logoutbtn);
		return lobtn;
	}
	
	
	
	public SearchResultPage doSearch(String value) {
		eleutil.doWaitElementLoad(searchbar, AppConstants.EXPECTED_MEDIUM_TIMEOUT);
		eleutil.doSendKeys(searchbar, value);
		eleutil.doclick(searchbtn);
		return new SearchResultPage(driver);
	}
	
}
