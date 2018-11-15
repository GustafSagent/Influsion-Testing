import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.java.swing.plaf.windows.resources.windows;


public class PR {
	
	WebDriver browser;

	//Run this code to setup the right test environmnet 
	@Before
	public void setUpTestEnviornment() {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		//Hermans config
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		browser= new ChromeDriver();
		 
	}
	
	@After
	public void endTesting() {
		browser.close();
	}


	
	@Test
	public void PR1() {
		boolean loadingcheck=false;
	
	long start = System.currentTimeMillis();

	browser.get("http://localhost:8080/");  

	long finish = System.currentTimeMillis();
	Long loadtime = (Long)((JavascriptExecutor)browser).executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
	if (loadtime<=2000) {
		loadingcheck=true;
		
	}
	assertEquals(true,loadingcheck);
	
	

	}
}
