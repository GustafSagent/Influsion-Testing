import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FRExtra{
	
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
	
	
	//FR31 Instagram post content
	@Test
	public void FR31() throws InterruptedException {
		
		List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
		for (WebElement filterButton : filterButtons) {
			if (filterButton.getAttribute("data-icon").equals("twitter") ||
				filterButton.getAttribute("data-icon").equals("youtube")) {
				if (filterButton.getAttribute("data-state").equals("active")) {
					filterButton.click();
				}
			}
		}
		
		List<WebElement> instaContent = browser.findElements(By.className("popular-component-wrapper"));
		instaContent.get(0).click();
		Thread.sleep(500);
		
		WebElement expand = instaContent.get(0).findElement(By.className("expanded-view"));
		
		//name
		String name = expand.findElement(By.xpath(".//div[@class='header']/a[1]")).getAttribute("href");
		//img
		String imgsrc = expand.findElement(By.className("content-container")).findElement(By.tagName("img")).getAttribute("src");
		//text
		String text = expand.findElement(By.className("content-container")).findElement(By.tagName("p")).getText();
		//username
		//verified account
		
		//assert that the strings are correct
		
	}
	
	/*
	//FR32 Instagram meta content
	@Test
	public void FR32() throws InterruptedException {
		
		List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
		for (WebElement filterButton : filterButtons) {
			if (filterButton.getAttribute("data-icon").equals("twitter") ||
				filterButton.getAttribute("data-icon").equals("youtube")) {
				if (filterButton.getAttribute("data-state").equals("active")) {
					filterButton.click();
				}
			}
		}
		
		List<WebElement> instaContent = browser.findElements(By.className("popular-component-wrapper"));
		instaContent.get(0).click();
		Thread.sleep(500);
		
		WebElement metaData = instaContent.get(0).findElement(By.className("meta-data"));
		List<WebElement> metaDataTypes = metaData.findElements(By.tagName("span"));
		ArrayList<String> metaDataStrings = new ArrayList<String>();
		
		for (WebElement data : metaDataTypes) {
			metaDataStrings.add(data.findElement(By.tagName("svg")).getAttribute("data-icon"));
		}
		
		ArrayList<String> reqStrings = new ArrayList<String>();
		reqStrings.add("heart");
		reqStrings.add("calendar-alt");
		//reqStrings.add("views");
		//reqStrings.add("comments");
		//check for hashtags
		
		assertTrue(metaDataStrings.containsAll(reqStrings));	
		
	}*/
	
	
}


