package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils eleutil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.eleutil = new ElementUtils(driver);
	}

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	
	
	public String doLoginPageCurrentUrlCheck() {
		String url = eleutil.doGetCurrentUrl();
		return url;
	}
	
	public String doLoginPageTitleCheck() {
		String title = eleutil.doGetTitle();
		return title;
	}
	
	public AccountPage doLoginCheck(String un, String pwd) {
		eleutil.doWaitElementLoad(email, AppConstants.EXPECTED_MEDIUM_TIMEOUT).sendKeys(un);;
		eleutil.doSendKeys(password, pwd);
		eleutil.doclick(loginBtn);
		return new AccountPage(driver);
	}

}
