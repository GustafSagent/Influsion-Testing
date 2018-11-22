

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FR1to11{
	WebDriver browser;
	String username;
	String password;
	String startUrl;

	//Run this code to setup the right test eniornmnet 
	@Before
	public void setUpTestEnviornment() throws InterruptedException {
		// /Users/Gustaf/Desktop/SeleniumDrivers/chromedriver
		//Server: chromedriver
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		
		ChromeOptions options = new ChromeOptions();  
//		options.addArguments("--headless");  
		browser = new ChromeDriver(options);
		username="jonas";
		password="1234";
		startUrl = "http://localhost:8080/";
		browser.get(startUrl);
		Thread.sleep(1000);
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	
	@Test
	public void FR1() throws InterruptedException {
		
		login(username, password, browser);
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		System.out.println(PopularFeed.size());
		
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(100,PopularComponent.size());  
		assertEquals(1,PopularFeed.size());
	}
	

	
//Test Case 2 dived up into 3 test cases to get a better view. At the moment we do only check the first post and see if it works. 
	@Test
	public void FR2_Youtube() throws InterruptedException {
		login(username, password, browser);

		List<WebElement> YoutubeContent =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		
		System.out.println(YoutubeContent.size());
		YoutubeContent.remove(YoutubeContent.size()-1);

		WebElement YoutubePost = YoutubeContent.get(randInt(0, YoutubeContent.size()-1));
		YoutubePost = YoutubePost.findElement(By.xpath(".."));
		System.out.println(YoutubePost.getAttribute("href"));
		
			String videolink = YoutubePost.getAttribute("href");
			((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", YoutubePost);
			((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-100)","");
			Thread.sleep(1000);			
			YoutubePost.click();
			
			String url = browser.getCurrentUrl();
			
			System.out.println(url);
			assertEquals(url.contains("youtube"),true);
		}
		
	
	@Test
	public void FR2_Twitter() throws InterruptedException {
			
		login(username, password, browser);
		
		List<WebElement> TwitterContent =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		TwitterContent.remove(TwitterContent.size()-1);
		System.out.println(TwitterContent.size());
		WebElement TwitterPost = TwitterContent.get(randInt(0, TwitterContent.size()-1));
		TwitterPost = TwitterPost.findElement(By.xpath(".."));	
		

		String postlink = TwitterPost.getAttribute("href");
		System.out.println(postlink);
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", TwitterPost);
		((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-100)","");
		Thread.sleep(200);
		TwitterPost.click();
		String url = browser.getCurrentUrl();
		System.out.println(url);
		assertEquals(url.contains("twitter"), true);
	}
		
		
	@Test
	public void FR2_Instagram() throws InterruptedException {
		
		login(username, password, browser);
			
		List<WebElement> InstaContent =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		InstaContent.remove(InstaContent.size()-1);
		WebElement InstaPost = InstaContent.get(randInt(0, InstaContent.size()-1));
		InstaPost = InstaPost.findElement(By.xpath(".."));	
//		System.out.println(InstaContent.size());
		
		
		String Instalink = InstaPost.getAttribute("href");
		System.out.println(Instalink);
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", InstaPost);
		((JavascriptExecutor) browser).executeScript("window.scrollBy(0,-100)","");
		Thread.sleep(200);
		InstaPost.click();
		String url = browser.getCurrentUrl();
		System.out.println(url);
		assertEquals(Instalink, url);
			}
		
		
	
	@Test
	public void FR3_instagram() throws InterruptedException {
		
		login(username, password, browser);
		
		Thread.sleep(1000);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		Button.get(Button.size()-1).click();
		
		
		browser.get(startUrl);
		Thread.sleep(1000);
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(1, Content.size());	
		
		
	}
	
	@Test
	public void FR3_twitter() throws InterruptedException {
		
		login(username, password, browser);
		
		Thread.sleep(1000);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		Button.get(Button.size()-1).click();
		
		
		browser.get(startUrl);
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(1, Content.size());	
		
	}
	
	@Test
	public void FR3_youtube() throws InterruptedException {
		
		login(username, password, browser);
		
		Thread.sleep(1000);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		Button.get(Button.size()-1).click();
		
		
		browser.get(startUrl);
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(1, Content.size());
		
	}
	
	@Test
	public void FR4_Twitter() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(1, Content.size());
		
	}
	
	@Test
	public void FR4_Instagram() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(1, Content.size());
		
	}
	
	@Test
	public void FR4_Youtube() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> Button =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		Button.get(Button.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(1, Content.size());
		
	}
	
	
	@Test
	public void FR5_Twitter() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='twitter']"));
		assertEquals(NrOfPostsPlusOne, Content.size());
		
		
	}
	
	@Test
	public void FR5_Youtube() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='youtube']"));
		assertEquals(NrOfPostsPlusOne, Content.size());
		
		
	}
	
	@Test
	public void FR5_Instagram() throws InterruptedException {
		
		login(username, password, browser);
		
		List<WebElement> ListOfContent =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		int NrOfPostsPlusOne=ListOfContent.size();
		ListOfContent.get(ListOfContent.size()-1).click();
		List<WebElement> Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(Content.size(),1);
		ListOfContent.get(ListOfContent.size()-1).click();
		Content =browser.findElements(By.cssSelector("[data-icon='instagram']"));
		assertEquals(NrOfPostsPlusOne, Content.size());
		
		
	}
	
	
	// Functions tested in Test Case 6 are not implemented yet - search
	@Test
	public void FR6() throws InterruptedException {
		login(username,password,browser);
		browser.findElement(By.cssSelector("[data-icon='search']")).click();
		browser.findElement(By.className("searchInput")).sendKeys("JustinBieber");
		Thread.sleep(300);
		assertEquals("Justin Bieber",browser.findElement(By.className("search-header")).getText());
		
		browser.get("http://localhost:8080/search");  
		browser.findElement(By.cssSelector("[data-icon='search']")).click();
		browser.findElement(By.className("searchInput")).sendKeys("JoeRogan");
		Thread.sleep(300);
		assertEquals("Joe Rogan",browser.findElement(By.className("search-header")).getText());

		browser.get("http://localhost:8080/search");  
		browser.findElement(By.cssSelector("[data-icon='search']")).click();
		browser.findElement(By.className("searchInput")).sendKeys("Felix Kjellberg");
		Thread.sleep(300);
		assertEquals("Felix Kjellberg",browser.findElement(By.className("search-header")).getText());

		
		
		
	}
	// Functions tested in Test Case 7 are not implemented yet - hashtag search
	
	
	@Test
	public void FR8() throws InterruptedException {
		browser.get("http://localhost:8080/register");
		Thread.sleep(1000);
		
		int uniqueUserID = randInt(1, 999);
		
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys("TestUser" + uniqueUserID);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("TestPassword1234");
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
		assertEquals(100, PopularComponent.size());  
		assertEquals(1, PopularFeed.size());
		
		
	}
	
	
	@Test
	public void FR9() throws InterruptedException {
		
		login(username, password, browser);
		Thread.sleep(1000);
		
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(100, PopularComponent.size());  
		assertEquals(1, PopularFeed.size());
				
	}
	
	@Test
	public void FR10() throws InterruptedException {
		
		ArrayList<WebElement> list = new ArrayList<WebElement>();
		list.add((browser.findElement(By.cssSelector("[placeholder='Username']"))));
		list.add(browser.findElement(By.cssSelector("[placeholder='Password']")));
		list.add(browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")));
		assertEquals(3,list.size());		
		
	}
	

	@Test
	public void FR11() throws InterruptedException {
		login(username, password, browser);
		browser.get("http://localhost:8080/");
		
		Thread.sleep(2000);
		List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		assertEquals(100, PopularComponent.size());  
		assertEquals(1, PopularFeed.size());
		
		
		
	}
	
	
	

	
	

	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void login(String username, String password, WebDriver browser) throws InterruptedException {
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(username);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")).click();
		Thread.sleep(1000);
	}
	
	




}
