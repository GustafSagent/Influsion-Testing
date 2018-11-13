import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FR12to22{
	
	WebDriver browser;

	//Run this code to setup the right test environment 
	@Before
	public void setUpTestEnviornment() {
		// Change webdriver file path to your own manually
		System.setProperty("webdriver.chrome.driver", "C:/Users/Herma/Desktop/SeleniumDriver/chromedriver.exe");
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");   
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
	
	@Test
	public void FR14() throws InterruptedException {
		
		Thread.sleep(500);
	
	
	}
}


