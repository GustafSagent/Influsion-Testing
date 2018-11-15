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
	
	/*
	@test
	public void FR24() {
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
	
	/*
	@test
	public void FR25() {
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
	
	
	//FR27 Twitter meta content
		@Test
		public void FR27() throws InterruptedException {
			
			List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
			for (WebElement filterButton : filterButtons) {
				if (filterButton.getAttribute("data-icon").equals("instagram") ||
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
			 
			String likes = "";
			String date = "";
			String retweets = "";
			String comments = "";
			String hashtags = "";
						
			for (WebElement data : metaDataTypes) {
				if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("heart")) {
					likes = data.getText();
				} else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("calendar-alt")) {
					date = data.getText();
				} //else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("retweet")) {
					//retweets = data.getText();
			//	} else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("comments")) {
					//comments = data.getText();
			//	}
				
				//where will the hashtags be shown?
						
			}
			
			boolean correctMeta = true;
			
			//likes
			if (!(Integer.parseInt(likes) >= 0)) {
				correctMeta = false;
			}
			//date
			if (date == null || date.equals("")) {
				correctMeta = false;
			}
			
			//comments
		//	if (!(Integer.parseInt(comments) >= 0)) {
		//		correctMeta = false;
		//	}
			//retweets
		//	if (!(Integer.parseInt(retweets) >= 0)) {
		//		correctMeta = false;
		//	}
			
			//verify that there are hashtags
			
			assertTrue(correctMeta);	
			
		}
		
		
		//FR28 twitter post content
		@Test
		public void FR28() throws InterruptedException {
			
			List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
			for (WebElement filterButton : filterButtons) {
				if (filterButton.getAttribute("data-icon").equals("instagram") ||
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
			//text
			String text = expand.findElement(By.className("content-container")).findElement(By.tagName("p")).getText();
			//img
			String imgsrc = expand.findElement(By.className("content-container")).findElement(By.tagName("img")).getAttribute("src");	
			//verified account (tag?)
		
			boolean correctContent = true;
			
			if (name.equals(null)) {
				correctContent = false;
			}
			if (text.equals(null)) {
				correctContent = false;
			}
			if (imgsrc.equals(null)) {
				correctContent = false;
			}
			//verified account (tag?)
			
			assertTrue(correctContent);		
		}
	
	
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
		//verified account
		//username
		
		boolean correctContent = true;
		
		if (name.equals(null)) {
			correctContent = false;
		}
		if (text.equals(null)) {
			correctContent = false;
		}
		if (imgsrc.equals(null)) {
			correctContent = false;
		}
		//username
		//verified account (tag?)
		
		assertTrue(correctContent);		
	}
	
	
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
		 
		String likes = "";
		String date = "";
		String comments = "";
		String hashtags = "";
		
		//where will the hashtags be shown?
		
		for (WebElement data : metaDataTypes) {
			if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("heart")) {
				likes = data.getText();
			} else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("calendar-alt")) {
				date = data.getText();
			} //else if (data.findElement(By.tagName("svg")).getAttribute("data-icon").equals("comments")) {
		//		comments = data.getText();
		//	} 
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
		
	//comments
	//	if (!(Integer.parseInt(comments) >= 0)) {
	//		correctMeta = false;
	//	}
	//verify that there are hashtags
			
		assertTrue(correctMeta);	
	}
	
}


