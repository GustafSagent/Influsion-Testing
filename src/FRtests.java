import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class FRtests{
	
	WebDriver browser;

	@Before
	public void setUpTestEnviornment() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Herma/Desktop/SeleniumDriver/chromedriver.exe");
		
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		browser = new ChromeDriver(chromeOptions);
		
		browser.get("http://localhost:8080/");
		login("testing12","testing12", browser);

		Thread.sleep(500);		
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	
	
	// ------------------LETS GO--------------------------
	
	//FR15 unfollow functionality
	@Test
	public void FR15() throws InterruptedException {
			
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
			
		//check if follow or not, if not then follow
		if (PopularComponent.get(0).findElement(By.className("fa-heart")).getAttribute("data-state").equals("active")) {
			//unfollow
			PopularComponent.get(0).findElement(By.className("fa-heart")).click();	
		} else {
			//already not followed
		}
			
		PopularComponent.get(0).click();
		Thread.sleep(200);
		String content[] = PopularComponent.get(0).getText().split("\\r?\\n");
		String nameKey = content[0];
			
		//go to follow page'
		browser.get("http://localhost:8080/");
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-heart")).click();
		Thread.sleep(500);

		//see if there are any posts from this influencer
		List<WebElement> FeedComponent = browser.findElements(By.className("feed-component-wrapper"));
		ArrayList<String> names = new ArrayList<String>();
		
		for (WebElement comp : FeedComponent) {		
			String conten[] = comp.getText().split("\\r?\\n");
			String name = conten[0];
			names.add(name);	
		}
			
		//check if the unfollowed influencer exist in the feed
		assertFalse(names.contains(nameKey));
			
	}
		
	@Test
	public void FR29() throws InterruptedException {

		browser.findElement(By.className("fa-star")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));

		for (WebElement filterButton : filterButtons) {
			if (filterButton.getAttribute("data-icon").equals("twitter") ||
				filterButton.getAttribute("data-icon").equals("instagram")) {
				if (filterButton.getAttribute("data-state").equals("active")) {
					filterButton.click();
				}
			}
		}
		
		List<WebElement> youtubeContent = browser.findElements(By.className("popular-component-wrapper"));
		youtubeContent.get(0).click();
		Thread.sleep(200);

		WebElement expand = browser.findElement(By.className("expanded-view"));

		//name
		String name = expand.findElement(By.xpath(".//div[@class='header']/a[1]")).getAttribute("href");

		//video
		String videosrc = expand.findElement(By.className("content-container")).findElement(By.tagName("iframe")).getAttribute("src");

		//text
		String text = expand.findElement(By.className("content-container")).findElement(By.tagName("p")).getText();
			
		boolean correctContent = true;
		
		if (name.equals(null)) {
			correctContent = false;
		}

		if (text.equals(null)) {
			correctContent = false;
		}

		if (videosrc.equals(null)) {
			correctContent = false;
		}	

			assertTrue(correctContent);		
		}
		
		
		//FR30 Youtube meta content-----not implement
		@Test
			public void FR30() throws InterruptedException {
			browser.findElement(By.className("fa-star")).click();

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));

			for (WebElement filterButton : filterButtons) {

				if (filterButton.getAttribute("data-icon").equals("twitter") ||
					filterButton.getAttribute("data-icon").equals("instagram")) {
					if (filterButton.getAttribute("data-state").equals("active")) {
						filterButton.click();
					}
				}
			}

			List<WebElement> youtubeContent = browser.findElements(By.className("popular-component-wrapper"));
			youtubeContent.get(0).click();
			Thread.sleep(300);

			WebElement metaData = browser.findElement(By.className("meta-data"));
			List<WebElement> metaDataTypes = metaData.findElements(By.tagName("span"));

			String likes = "";

			String date = "";

			String comments = "";

			String hashtags = "";

			for (WebElement data : metaDataTypes) {

				if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("heart")) {
					likes = data.getText();
				} else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("calendar-alt")) {
					date = data.getText();
				}
			}
			
			boolean correctMeta = true;

			//likes
			if (!(Integer.parseInt(likes) >= 0)) {
				correctMeta = false;
			}

			//date
			if (date == null) {
				correctMeta = false;
			}
			
			assertTrue(correctMeta);
		}	

	//FR37 Admin login
	@Test
	public void FR37() throws InterruptedException {		
				
		logout(browser);
		browser.get("http://localhost:8080/admin");
		adminLogin("admin", "1234", browser);
		String titleText = browser.findElement(By.className("admin-title")).getText();
		String content[] = titleText.split("\\r?\\n");
		String title = content[0];
		assertEquals("inFlusion: Admin", title);
		
	}
	
	//FR38 Admin stays logged in
		@Test
		public void FR38() throws InterruptedException {		
					
			logout(browser);
			browser.get("http://localhost:8080/admin");
			adminLogin("admin", "1234", browser);
			browser.get("http://localhost:8080/admin");

			String titleText = browser.findElement(By.className("admin-title")).getText();
			String content[] = titleText.split("\\r?\\n");
			String title = content[0];
			assertEquals("inFlusion: Admin", title);
			
		}
		
		//FR40 logout admin
		@Test
		public void FR40() throws InterruptedException {		
					
			logout(browser);
			browser.get("http://localhost:8080/admin");
			adminLogin("admin", "1234", browser);
			browser.findElement(By.className("admin-log-out")).click();
			Thread.sleep(1000);
			
			ArrayList<String> texts = new ArrayList<String>();

			String text = browser.findElement(By.className("admin-login-container")).getText();
			String content[] = text.split("\\r?\\n");
			for (String s : content) {
				texts.add(s);
			}
			assertTrue(texts.contains("Admin Login"));
			
		}


	//FR22 Promote Specific Influencer
	
	public static void login(String username, String password, WebDriver browser) throws InterruptedException {
		
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(username);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")).click();;
		Thread.sleep(1000);
	}
	
	public static void adminLogin(String username, String password, WebDriver browser) throws InterruptedException {
		
		browser.findElement(By.cssSelector("[placeholder='username']")).sendKeys(username);
		browser.findElement(By.cssSelector("[placeholder='password']")).sendKeys(password);
		browser.findElement(By.xpath("//button[contains(text(),'Login')]")).click();;
		Thread.sleep(1000);
	}
	
	public static void logout(WebDriver browser) {

		//go to settings page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-cogs")).click();
		browser.findElement(By.tagName("button")).click();
	}
	
	
}


