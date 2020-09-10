package com.tests;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.NdtvMainPage;
import com.pages.NdtvWeatherPage;
import com.utilities.BaseClass;
import com.utilities.CityException;

@SuppressWarnings("unused")
public class Test_TemperatureComparison extends BaseClass {

@Test
public void temperature_test() throws Exception {
	
	NdtvMainPage mp =  new NdtvMainPage();
	mp.clickSubMenu();
	mp.clickWeatherLink();
	Thread.sleep(5000);
	NdtvWeatherPage wp = new NdtvWeatherPage();
	String temp = wp.pinCity("Jammu");
	System.out.println("Jammu Temperature is :"+temp);
	boolean PopUpStatus = wp.SelectCityOnMap();
	
	if(PopUpStatus)
		System.out.println("Test Pass");
	else
		Assert.fail("Popup display Error");
	
}
	
}
