package com.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sun.xml.txw2.Document;
import com.utilities.BaseClass;
import com.utilities.CityException;
import com.utilities.ScreenShotClass;

public class NdtvWeatherPage extends BaseClass {

	@FindBy(xpath="//*[@id='searchBox']")
	private WebElement searchBox;
	
	@FindBy(xpath="//*[@id='messages']/div/label")
	private List<WebElement> citiesList;
	
/*	@FindBy(xpath="//*[@id='map_canvas']/div[1]/div[4]/div/div")
	private List<WebElement> onMapList;*/
	
	public List<WebElement> onMapList = new ArrayList<WebElement>();
	
/*	@FindBy(xpath="//*[@id='map_canvas']/div[1]/div[6]/div/div[1]/div/div/span/b")*/
	public List<WebElement> weatherPopContents = new ArrayList<WebElement>();
	
	@FindBy(xpath="//*[@id='map_canvas']/div[1]/div[6]/div/div[1]/div")
	private WebElement weatherPopup;
	
	public  List<String> l=null;
	
	
	public void NdtvMainPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String pinCity(String city) {
		
		String temp = null;
		
		boolean flag= false;

	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	js.executeScript("document.getElementById('searchBox').value='"+city+"';");
					
		List<WebElement> cities = driver.findElements(By.xpath("//*[@id='messages']/div/label"));
		
		for(WebElement c : cities) {
			
			String ActualCity = c.getText().trim().toString();
			
			if(ActualCity.equalsIgnoreCase(city)) {
				
				String cityXpath ="//input[@id='"+city+"']";
				
				System.out.println(cityXpath);
				
				BaseClass.driver.findElement(By.xpath(cityXpath)).click();
				
				System.out.println("Selected the City");
				
				break;
			}
			
		}
		
		onMapList.addAll(driver.findElements(By.xpath("//*[@id='map_canvas']/div[1]/div[4]/div/div")));
		
		
		for(WebElement c :onMapList) {
			
			String title = c.getAttribute("title").trim().toString();
			
			 temp = c.findElement(By.xpath(".//div//span[@class='tempRedText']")).getText().toString();
			
	         if((city.equalsIgnoreCase(title))&&(!(temp.isEmpty()))) {
	        	 
	        	 flag= true;
	        	 
	        	 temp = temp.substring(0,2).concat(" Celsius");
				
	        	 System.out.println("Selected City is visible on the map with Temperature");
	        	 Reporter.log("Selected City is visible on the map with Temperature");
	        	 
	        	 break;
			}
		}
		
		l = new ArrayList<String>();
		
		for(WebElement c :onMapList) {
			
			l.add(c.getAttribute("title").trim());
		}
		
		if(flag)
			System.out.println("");
		else
			Assert.fail("Pinned City is not displayed on the weather map");
		
		return temp;
		
	}
	
	
	public boolean SelectCityOnMap() throws Exception {
		
		boolean flag = false;
		
		System.out.println("Cities on Map..:"+l);
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter a city from above city list :");
		
		String cityonMap =scn.next();
		
		List<WebElement> mapCities = onMapList;
		
	       for(WebElement c :mapCities) {
			
			String title = c.getAttribute("title").trim();
			
	         if(cityonMap.equalsIgnoreCase(title)) {
	        	 
	        	 c.click();
	        	 
	        	 weatherPopup = driver.findElement(By.xpath("//*[@id='map_canvas']/div[1]/div[6]/div/div[1]"));
	        	 
	        	 if(weatherPopup.isDisplayed()) {
	        		 
	        		 flag= true;
	        		 
	        		 ScreenShotClass.takeSnapShot(driver,propMap.get("ScreenShotPath"));
	        		 
	        		 System.out.println("Weather Popup displayed");
	        		 
	        		 Reporter.log("Weather Popup displayed");
	        		 
	        		 weatherPopContents.addAll(driver.findElements(By.xpath("//*[@id='map_canvas']/div[1]/div[6]/div/div[1]/div/div/span/b")));
	        		 
	        		 System.out.println("Popup Data:");
	        		 
	        		for(WebElement ele : weatherPopContents) {
	        			
	        			System.out.println(ele.getText());
	        		}
	        		 
	        	 }
	        	 
	        	 else {
	        		 
	        		 Assert.fail("Selected city Weather DisplayPopUp error");
	        	 }
	        	 
	          break;
	        	 
			}
			
		}
		return flag;
		
	}

}