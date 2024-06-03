package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.framework.FrameworkExecption;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initBrowser(Properties prop) {
		OptionManager op = new OptionManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browser = prop.getProperty("browser");
		System.out.println("Launched browser Name : " + browser);
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(op.getChormeOption()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(op.getEdgeOption()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(op.getFirefoxOption()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Please pass the valid Browser Details...!" + browser);
			break;
		}

		gettlDriver().manage().window().maximize();
		gettlDriver().manage().deleteAllCookies();
		gettlDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return gettlDriver();
	}

	public static synchronized WebDriver gettlDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		FileInputStream ip = null;
		prop = new Properties();
		String Envname = System.getProperty("env");
		try {
			if (Envname == null) {
				ip = new FileInputStream("./src/test/resources/config/qaconfig.properties");
			} else if (Envname.equalsIgnoreCase("stg")) {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else if (Envname.equalsIgnoreCase("prod")) {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				throw new FrameworkExecption("Pass the valid Environment details....!");
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FrameworkExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot() {
		File srcfile = ((TakesScreenshot) gettlDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File despath = new File(path);

		try {
			FileUtil.copyFile(srcfile, despath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

}
