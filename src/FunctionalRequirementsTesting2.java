import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FunctionalRequirementsTesting2{
	WebDriver browser;

	//Run this code to setup the right test eniornmnet 
	@Before
	public void setUpTestEnviornment() {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		// /Users/Gustaf/Desktop/SeleniumDrivers/chromedriver
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");

		//Gustafs Config
		//System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		//Hermans config
		//System.setProperty("webdriver.chrome.driver", "/Users/Herma/Desktop/chromedriver");
	
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");   
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	

	
//	@Test
//	public void TestingTitle() {
//
//	String name = browser.getTitle();   
//	assertEquals("Pumba",name);   
//	
//
//	}
//	
//	
//	@Test
//	public void FR1() {
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
//	List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
////	for (int i=0; i<PopularComponent.size();i++) {
////		System.out.println(PopularComponent.get(i).getText());
////		System.out.println("COUNT: " + i);	
////	}
////	System.out.println(PopularComponent.size());
//	assertEquals(PopularComponent.size(),100);  
//	assertEquals(PopularFeed.size(), 1);
//	}
//	

	@Test
	public void FR2() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> YoutubeContent =browser.findElements(By.cssSelector("a[href*=youtube]"));
		
		
		System.out.println(YoutubeContent);
		for (int i=0; i<YoutubeContent.size();i++) {
			
			if (YoutubeContent.get(i).isDisplayed()) {
				System.out.println("isDisplayed" + "  Before");
			}
			
			
			Actions actions = new Actions(browser);
			actions.moveToElement(YoutubeContent.get(i));
			
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (YoutubeContent.get(i).isDisplayed()) {
				System.out.println("isDisplayed");
			}
			
		//	YoutubeContent.get(i).click();
			
			
		
		
		System.out.println(YoutubeContent.get(i));
		
		}
		
		System.out.println(YoutubeContent.size());
		assertEquals(1, 1);
	
		}


}
