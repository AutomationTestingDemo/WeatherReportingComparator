package com.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.BaseClass;

public class NdtvMainPage extends BaseClass {
	
	@FindBy(id="h_sub_menu")
	private WebElement submenu;
	
	@FindBy(xpath="//a[text()='WEATHER']")
	private WebElement weatherLink;
	
	
	public NdtvMainPage()
	{
		PageFactory.initElements(driver, this);
	}
	public NdtvMainPage clickSubMenu()
	{
		submenu.click();
		return this;
	}
	public void clickWeatherLink()
	{
		weatherLink.click();
	}

}
