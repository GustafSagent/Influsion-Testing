import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FR12to22{
	
	WebDriver browser;

	@Before
	public void setUpTestEnviornment() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Herma/Desktop/SeleniumDriver/chromedriver.exe");
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");	
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
		
		helpFunctions.login("test1","test1", browser);
		browser.get("http://localhost:8080/");	
		Thread.sleep(500);		
		
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
		browser.get("http://localhost:8080/feed");
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
	
		helpFunctions.login("test1","test1", browser);
		browser.get("http://localhost:8080/");
		Thread.sleep(500);
		
		//go to follow page'
		browser.get("http://localhost:8080/feed");
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
				if (!date.after(timestamps.get(indx+1))) {
					correctOrder = false;
				}	
			}
		}	
		
		assertEquals(correctOrder, true);

	}
	
	
	//FR15 unfollow functionality
	@Test
	public void FR15() throws InterruptedException {
		
		helpFunctions.login("test1","test1", browser);
		browser.get("http://localhost:8080/");
		Thread.sleep(500);
		
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
		browser.get("http://localhost:8080/feed");
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
	
	
	//FR17 TV operator settings page and functionality
	
	
	//FR18 Logout
	
	
	//FR19 Change personal information in settings menu
	
	
	//20-21 Delete account functionality
	
	
	//FR22 Promote Specific Influencer
	
}


