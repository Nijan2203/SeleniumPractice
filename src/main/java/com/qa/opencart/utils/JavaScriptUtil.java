package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void flash(WebElement element) {
		String color = element.getCssValue("background-color");
		for (int i = 0; i < 10; i++) {
			changecolor(element, "rgp(80, 250, 80)");
			changecolor(element, color);
		}
	}

	public void changecolor(WebElement element, String color) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}

	}
}
