import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FR1to11{
	WebDriver browser;

	//Run this code to setup the right test eniornmnet 
	@Before
	public void setUpTestEnviornment() {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		// /Users/Gustaf/Desktop/SeleniumDrivers/chromedriver
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");   
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	

	
	
	
	@Test
	public void FR1() throws InterruptedException {
		
		Thread.sleep(200);
		
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(PopularComponent.size(),100);  
		assertEquals(PopularFeed.size(), 1);
	}
	

	
//Test Case 2 dived up into 3 test cases to get a better view. At the moment we do only check the first post and see if it works. 
	@Test
	public void FR2_1() throws InterruptedException {
	
			Thread.sleep(1000);
		
		List<WebElement> YoutubeContent =browser.findElements(By.cssSelector("a[href*=youtube]"));
		WebElement YoutubePost = YoutubeContent.get(0);
		
			String videolink = YoutubePost.getAttribute("href");
			((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", YoutubePost);
			((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-50)","");
			Thread.sleep(200);
			YoutubePost.click();
			String url = browser.getCurrentUrl();
			System.out.println(url);
			assertEquals(url,videolink);
		}
		
	
	@Test
	public void FR2_2() throws InterruptedException {
			
		Thread.sleep(1000);
		
		
		List<WebElement> TwitterContent =browser.findElements(By.cssSelector("a[href*=twitter]"));	
		WebElement TwitterPost = TwitterContent.get(0);

		String postlink = TwitterPost.getAttribute("href");
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", TwitterPost);
		((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-50)","");
		Thread.sleep(200);
		TwitterPost.click();
		String url = browser.getCurrentUrl();
		System.out.println(url);
		assertEquals(url,postlink);
	}
		
		
	@Test
	public void FR2_3() throws InterruptedException {
		
		Thread.sleep(1000);
			
		List<WebElement> InstaContent =browser.findElements(By.cssSelector("a[href*=instagram]"));
		WebElement InstaPost = InstaContent.get(0);
		String Instalink = InstaPost.getAttribute("href");
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", InstaPost);
		((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-50)","");
		Thread.sleep(200);
		InstaPost.click();
		String url = browser.getCurrentUrl();
		System.out.println(url);
		assertEquals(url,Instalink);
			}
		
		



}
