import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FR23to32{
	
	WebDriver browser;
	String username;
	String password;

	//Run this code to setup the right test environmnet 
	@Before
	public void setUpTestEnviornment() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Herma/Desktop/SeleniumDriver/chromedriver.exe");
		
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		browser = new ChromeDriver(chromeOptions);
		
		username="testing12";
		password="testing12";
		browser.get("http://localhost:8080");  
        helpFunctions.login(username, password, browser);
	}

	@After
	public void endTesting() {
		browser.close();
	}
	
	
	//FR23:TV operator
	
	@Test
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
	}
	
	
	//FR25:search result-----Done
	@Test
	public void FR25() {

       
		browser.findElement(By.className("fa-search")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement SearchInput = browser.findElement(By.className("searchInput"));	
		String input=SearchInput.getAttribute("value");
		//System.out.println("sss+"+input);
		List<WebElement> suggestions = browser.findElements(By.className("search-header"));
		for(WebElement t:suggestions) {
			String content = t.getText().substring(0,1);
			assertEquals(input.toUpperCase(),content);
		}

		
		//assertEquals(SearchInput.getText() ,"Welcome");	
	}
	
	
	//FR26:TV operator-----not implement
	/*@Test
	public void FR26() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//browser.findElement(By.className("fa-instagram")).click();
		//browser.findElement(By.className("fa-youtube")).click();
		List<WebElement> PopularComponent = browser.findElements(By.className("popular-component-wrapper"));
		PopularComponent.get(1).click();
		WebElement CheckInfor = PopularComponent.get(1).findElement(By.className("meta-data"));
		List<WebElement> metadata = CheckInfor.findElements(By.tagName("span"));
		//ArrayList<WebElement> list = new ArrayList<WebElement>(); 
		for(WebElement t:metadata) {
			String content = t.getText();
			//if(t.findElement(By.tagName("data-icon")).equals("heart")) {
				//Integer.parseInt(content); 
				//System.out.println(content);
				//assertTrue(Integer.parseInt(content)>=0);
			//}
			System.out.println(content);
		}
		//WebElement content = metadata.
		
		//System.out.println(metadata.);
		//assertNotNull(CheckInfor);	
	}*/
	
	@Test
	public void FR27() throws InterruptedException {
		browser.findElement(By.className("fa-star")).click();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		Thread.sleep(300);


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

				
				browser.findElement(By.className("fa-star")).click();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

				Thread.sleep(300);

				

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

				if (text.equals(null)&&imgsrc.equals(null)) {

					correctContent = false;

				}

	

				//verified account (tag?)

				

				assertTrue(correctContent);		

			}
			
			

	//FR29 Youtube post content-----Not implement

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
				
		//FR31 Instagram post content

		@Test

		public void FR31() throws InterruptedException {

			
			browser.findElement(By.className("fa-star")).click();
			List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

			
			browser.findElement(By.className("fa-star")).click();
			List<WebElement> filterButtons = browser.findElement(By.className("filter")).findElements(By.tagName("svg"));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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


