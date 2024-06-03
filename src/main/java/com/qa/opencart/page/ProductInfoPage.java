package com.qa.opencart.page;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtils eleutil;
	private LinkedHashMap<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtils(driver);
	}

	private By header = By.tagName("h1");
	private By imgCount = By.xpath("//li[@class ='image-additional']/a/img");
	private By prdMetaData = By.xpath("//div[@class = 'col-sm-4']//ul[@class='list-unstyled'][1]/li");
	private By prdPriceData = By.xpath("//div[@class = 'col-sm-4']//ul[@class='list-unstyled'][2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By sucmsg = By.xpath("//div[@class = 'alert alert-success alert-dismissible']");
	
	public String getProductHeader() {
		String head = eleutil.dogetText(header);
		return head;
	}

	public int getImageCount() {
		int imgcont = eleutil.doFindElements(imgCount).size();
		return imgcont;
	}

	public LinkedHashMap<String, String> getproductinfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("productHeader", getProductHeader());
		getmetaData();
		getPriceData();
		return productInfoMap;
	}

	public void getmetaData() {
		List<WebElement> metadata = eleutil.doFindElements(prdMetaData);
		for (WebElement e : metadata) {
			String txt = e.getText();
			String meta[] = txt.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productInfoMap.put(key, value);
		}

	}

	public void getPriceData() {
		List<WebElement> pricedata = eleutil.doFindElements(prdPriceData);
		String price = pricedata.get(0).getText();
		String Exprice = pricedata.get(1).getText();
		String pce[] = Exprice.split(":");

		productInfoMap.put("Productprice", price);
		productInfoMap.put(pce[0].trim(), pce[1].trim());

	}
	
	public String doAddToCart() {
		eleutil.doSendKeys(quantity, AppConstants.EXPECTED_QUANTITY_COUNT);
		eleutil.doclick(addToCart);
		String msgsuccess = eleutil.doWaitElementLoad(sucmsg, AppConstants.EXPECTED_MEDIUM_TIMEOUT).getText();
		StringBuilder str = new StringBuilder(msgsuccess);
	String utdmsg = str.substring(0, msgsuccess.length()-2);
		return utdmsg;
	}
}
