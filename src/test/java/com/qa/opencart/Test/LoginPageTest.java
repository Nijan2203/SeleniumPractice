package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.page.AccountPage;

public class LoginPageTest extends BaseTest {

//	private WebDriver driver;

	@Test(priority = 1)
	public void doLoginPageCurrentUrlTest() {
		String url = loginPage.doLoginPageCurrentUrlCheck();
		Assert.assertEquals(url, AppConstants.EXPECTED_LOGIN_PAGE_URL);
	}

	@Test(priority = 2)
	public void doLoginPageTitleTest() {
		String title = loginPage.doLoginPageTitleCheck();
		Assert.assertEquals(title, AppConstants.EXPECTED_LOGIN_PAGE_TITLE);
	}

	@Test(priority = 3)
	public void doLoginTest() {
		AccountPage accPage = loginPage.doLoginCheck(prop.getProperty("username"), prop.getProperty("password"));
		boolean check = accPage.doLogoutBtnCheck();
		Assert.assertEquals(check, true);

	}

}
