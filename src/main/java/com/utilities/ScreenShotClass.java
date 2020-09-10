package com.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotClass {
	
	 public static void takeSnapShot(WebDriver driver,String filepath) throws Exception{

	        TakesScreenshot capture =((TakesScreenshot)driver);

	        File srcfile=capture.getScreenshotAs(OutputType.FILE);
	        
	        String dirPath = System.getProperty("user.dir");

	        File destfile=new File(dirPath+File.separator+filepath);

	        FileUtils.copyFile(srcfile, destfile);
	        
	        System.out.println("Popup ScreenShot Taken");

	    }

}
