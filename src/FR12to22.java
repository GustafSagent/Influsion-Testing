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


public class FR12to22{
	
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
	
	
	//FR12 retrieve lost password via email
	
	
	//FR13 following functionality
	@Test
	public void FR13() throws InterruptedException {		
		
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		
		//check if follow or not, if not then follow
		if (PopularComponent.get(0).findElement(By.className("fa-heart")).getAttribute("data-state").equals("active")) {
			// already followed
		} else {
			//click the follow button
			PopularComponent.get(0).findElement(By.className("fa-heart")).click();	
		}
		
		PopularComponent.get(0).click();
		Thread.sleep(200);
		String content[] = PopularComponent.get(0).getText().split("\\r?\\n");
		String nameKey = content[0];
		
		//go to follow page'
		browser.findElement(By.className("blur-overlay")).click();
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
		
		//check if the followed influencer exist in the feed
		assertTrue(names.contains(nameKey));
	}
	
	
	//FR14 check order of posts
	@Test
	public void FR14() throws InterruptedException, ParseException, NoSuchElementException {
		
		//go to follow page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-heart")).click();
		Thread.sleep(500);
		
		List<WebElement> FeedComponent = browser.findElements(By.className("feed-component-wrapper"));
		ArrayList<Date> timestamps = new ArrayList<Date>();
		boolean correctOrder = true;
		
		for (WebElement comp : FeedComponent) {
			String time = comp.findElement(By.tagName("time")).getAttribute("datetime");	
			String times[] = time.split("T");
			String newtime = times[0] + " " + times [1];
			Date datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(newtime);
			timestamps.add(datetime);	
		}
		
		for (Date date : timestamps) {
			int indx = timestamps.indexOf(date);
			if (indx < timestamps.size()-1) {	
				if (date.compareTo(timestamps.get(indx+1)) < 0) {
					correctOrder = false;
				}	
			}
		}	
		
		assertEquals(true, correctOrder);
	}
	
	
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
	
	
	//FR16 settings page and functionality
	@Test
	public void FR16() throws InterruptedException {		
				
		//go to settings page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-cogs")).click();
		Thread.sleep(500);
		
		WebElement settings = browser.findElement(By.tagName("a"));
		String settingsPage = settings.getText();
		
		boolean exists = false;
		
		if (settingsPage.equals("Edit information")) {
			settings.click();
			exists = true;
		}
		
		assertTrue(exists);
	}	
	

	//FR18 Logout
	@Test
	public void FR18() throws InterruptedException {		
				
		//go to settings page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-cogs")).click();
		browser.findElement(By.tagName("button")).click();
		assertTrue(browser.findElements(By.className("login")).size() > 0);

	}
	
	
	//FR19 Change personal information in settings menu
	@Test
	public void FR19() throws InterruptedException {		
		
		//go to settings page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-cogs")).click();

		// not done because not implemented
		
	}

	
	//20-21 Delete account functionality
	@Test
	public void FR20_21() throws InterruptedException {		
		
		//go to settings page'
		browser.findElement(By.className("subFooter")).findElement(By.className("fa-cogs")).click();
		List<WebElement> buttons = browser.findElements(By.tagName("a"));
		ArrayList<String> buttonText = new ArrayList<String>();
		for (WebElement button : buttons) {
			if (button.getText().equals("Delete account")) {
				button.click();
				Thread.sleep(1000);		
			}
		}
		
		//not done because not implemented
	}

	
	//FR22 Promote Specific Influencer
	
	public static void login(String username, String password, WebDriver browser) throws InterruptedException {
		
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(username);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]")).click();;
		Thread.sleep(1000);
	}
	
}


