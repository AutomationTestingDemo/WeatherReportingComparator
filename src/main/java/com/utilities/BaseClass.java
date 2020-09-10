package com.utilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

@SuppressWarnings("unused")
public class BaseClass {
	
	public static Map<String, String> propMap=null;
	public static WebDriver driver;
	
	public BaseClass() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeSuite
	public void beforeSuite() throws IOException { //ITestContext testContext
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("==========+++++++++Execution Started at : "+dateFormat.format(date)+"+++++++++==========");
		ResourceConfig.loadProperties();
		propMap = ResourceConfig.getPropMap();
		
		  String browserType = propMap.get("Browser");
			
			if(browserType.equals("GC"))
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_PATH);
				driver= new ChromeDriver();
			}
			else
			{
				System.setProperty("webdriver.gecko.driver", Constants.FF_PATH);
				driver = new FirefoxDriver();
			}
}
	@BeforeTest
	public void preTest() {
         System.out.println("Before Test PreConditions");
		driver.get(propMap.get("NDTVurl"));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@AfterTest
	public void postTest() {
		System.out.println("After Test Post Conditions");
		driver.quit();
	}
	

	
}