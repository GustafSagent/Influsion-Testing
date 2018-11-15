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
	public void setUpTestEnviornment() throws InterruptedException {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		// /Users/Gustaf/Desktop/SeleniumDrivers/chromedriver
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");  
		Thread.sleep(1000);
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	

	
	
	
	@Test
	public void FR1() throws InterruptedException {
		
		
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(PopularComponent.size(),100);  
		assertEquals(PopularFeed.size(), 1);
	}
	

	
//Test Case 2 dived up into 3 test cases to get a better view. At the moment we do only check the first post and see if it works. 
	@Test
	public void FR2_Youtube() throws InterruptedException {

		
		List<WebElement> YoutubeContent =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		YoutubeContent.remove(YoutubeContent.size()-1);
		WebElement YoutubePost = YoutubeContent.get(helpFunctions.randInt(0, YoutubeContent.size()-1));
		YoutubePost = YoutubePost.findElement(By.xpath(".."));
		
			String videolink = YoutubePost.getAttribute("href");
			((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", YoutubePost);
			((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-50)","");
			Thread.sleep(1000);
			YoutubePost.click();
			String url = browser.getCurrentUrl();
			System.out.println(url);
			assertEquals(url,videolink);
		}
		
	
	@Test
	public void FR2_Twitter() throws InterruptedException {
			
		
		List<WebElement> TwitterContent =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		TwitterContent.remove(TwitterContent.size()-1);
		WebElement TwitterPost = TwitterContent.get(helpFunctions.randInt(0, TwitterContent.size()-1));
		TwitterPost = TwitterPost.findElement(By.xpath(".."));	
		

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
	public void FR2_Instagram() throws InterruptedException {
			
		List<WebElement> InstaContent =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		InstaContent.remove(InstaContent.size()-1);
		WebElement InstaPost = InstaContent.get(helpFunctions.randInt(0, InstaContent.size()-1));
		InstaPost = InstaPost.findElement(By.xpath(".."));	
		
		
		
		String Instalink = InstaPost.getAttribute("href");
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", InstaPost);
		((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-50)","");
		Thread.sleep(200);
		InstaPost.click();
		String url = browser.getCurrentUrl();
		System.out.println(url);
		assertEquals(url,Instalink);
			}
		
		
	 //FR3 not implemented yet
	
	@Test
	public void FR4_Twitter() throws InterruptedException {
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(Content.size(),1);
		
	}
	
	@Test
	public void FR4_Instagram() throws InterruptedException {
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(Content.size(),1);
		
	}
	
	@Test
	public void FR4_Youtube() throws InterruptedException {
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(Content.size(),1);
		
	}
	
	
	@Test
	public void FR5_Twitter() throws InterruptedException {
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(Content.size(),NrOfPostsPlusOne);
		
		
	}
	
	@Test
	public void FR5_Youtube() throws InterruptedException {
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(Content.size(),NrOfPostsPlusOne);
		
		
	}
	
	@Test
	public void FR5_Instagram() throws InterruptedException {
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(Content.size(),NrOfPostsPlusOne);
		
		
	}
	
	// Functions tested in Test Case 6 are not implemented yet
	// Functions tested in Test Case 7 are not implemented yet
	
	
	@Test
	public void FR8() throws InterruptedException {
		browser.get("http://localhost:8080/register");
		Thread.sleep(1000);
		
		int uniqueUserID = helpFunctions.randInt(100000, 999999);
		
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys("TestUser" + uniqueUserID);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("TestPassword");
		browser.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("TestUser"  + uniqueUserID + "@Test.org");
		browser.findElement(By.cssSelector("[placeholder='Age']")).sendKeys("25");
		browser.findElement(By.cssSelector("[name='sex']")).sendKeys("Female");
		
		browser.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		
		
		browser.get("http://localhost:8080/login");
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys("TestUser" + uniqueUserID);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("TestPassword");
		Thread.sleep(1000);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")).click();
		
		Thread.sleep(1000);
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(PopularComponent.size(),100);  
		assertEquals(PopularFeed.size(), 1);
		
		
	}
	
	
	@Test
	public void FR9() throws InterruptedException {
		
		browser.get("http://localhost:8080/login");
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys("test");
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("testtest");
		Thread.sleep(1000);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")).click();
		Thread.sleep(1000);
		
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(PopularComponent.size(),100);  
		assertEquals(PopularFeed.size(), 1);
				
	}
	
	

	
	
	
	




}
