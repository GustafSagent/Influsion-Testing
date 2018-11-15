import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FR23to32{
	
	WebDriver browser;

	//Run this code to setup the right test environmnet 
	@Before
	public void setUpTestEnviornment() {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		//Hermans config
		//System.setProperty("webdriver.chrome.driver", "C:/Users/Herma/Desktop/SeleniumDriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");		
		browser= new ChromeDriver();
		browser.get("http://localhost:8080/");   
	}
	
	@After
	public void endTesting() {
		browser.close();
	}
	
	
	/*@Test
	public void TestingTitle() {

	String name = browser.getTitle();   
	assertEquals("Pumba",name);   

	}*/
	
	
	@Test
	/*public void FR1() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	List<WebElement> PopularFeed = browser.findElements(By.cssSelector(".popular-feed-content"));
	List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));

	assertEquals(PopularComponent.size(),100);  
	assertEquals(PopularFeed.size(), 1);
	
		
	}*/
	/*public void FR24() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		browser.findElement(By.className("fa-search")).click();
		WebElement Searchexp = browser.findElement(By.className("info-text"));	
		assertEquals(Searchexp.getText().substring(0,7) ,"Welcome");	
	}*/
	/*public void FR25() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		browser.findElement(By.className("fa-search")).click();
		WebElement SearchInput = browser.findElement(By.className("searchInput"));	
		System.out.println("sss"+SearchInput.getAttribute("value"));
		//assertEquals(SearchInput.getText() ,"Welcome");	
	}*/
	public void FR27() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		PopularComponent.get(1).click();
		String CheckInfor = PopularComponent.get(1).findElement(By.className("meta-data")).getText();
		//System.out.println(CheckInfor);
		assertNotNull(CheckInfor);	
	}
	
}


