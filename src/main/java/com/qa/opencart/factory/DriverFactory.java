package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author ecom-sarath.kumar
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webDriver on the basis of a given
	 * browser name
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();

		optionsManager = new OptionsManager(prop);

		System.out.println("Browser Name is : " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());

			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();// no headless support
			tlDriver.set(new SafariDriver());

		} else {
			System.out.println("Your Browser name is incorrect, please enter a valid browser name");
			
		}
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url").trim());

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());

		// return driver;
		return getDriver();
	}

	/**
	 * get the thread local copy of the driver
	 * 
	 * @return
	 */

	public static WebDriver getDriver() {

		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties
	 * 
	 * @return Properties
	 */
	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("Running test on environment: " + envName);

		if (envName == null) {

			System.out.println("No env is given... hence running it on QA environment....");

			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("Running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("Running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					System.out.println("Running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					System.out.println("Running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					System.out.println("Running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right environment...");
					throw new FrameworkException("No Env is found excepion");
					//break;
				}

			} catch (Exception e) {

			}

		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { ip = new FileInputStream(
		 * "/Users/ecom-sarath.kumar/Documents/JavaSeleniumPractice2022/March2022POMSeries/src/test/resources/config/config.properties"
		 * ); //prop = new Properties(); prop.load(ip);
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */

		return prop;

	}

	/*
	 * Take Screenshot
	 * 
	 */

	public String getScreenshot() {

		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
