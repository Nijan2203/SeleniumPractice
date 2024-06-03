package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtils {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement doFindElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> doFindElements(By locator) {
		return driver.findElements(locator);
	}

	public String doGetTitle() {
		return driver.getTitle();
	}

	public String doGetCurrentUrl() {
		return driver.getCurrentUrl();

	}

	public WebElement doWaitElementLoad(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement webele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		doFindElement(locator);
		return webele;
		
	}

	public List<WebElement> doWaitElementsLoad(By locator, int timeout) {
		doFindElements(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void doSendKeys(By locator, String value) {
		doFindElement(locator).clear();
		doFindElement(locator).sendKeys(value);

	}

	public void doclick(By locator) {
		doFindElement(locator).click();
	}
	
	
	public boolean dodisplaycheck(By locator) {
		return doFindElement(locator).isDisplayed();
	}
	
	public String  dogetText(By locator) {
		return doFindElement(locator).getText();
	}
	
}