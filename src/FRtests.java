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


