import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckType {

	@Test
	public void test() {
	// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		WebDriver browser= new ChromeDriver();
		browser.get("http://localhost:8080/");   

	String name = browser.getTitle();   
	assertEquals("Pumba",name);   
	browser.close();

	}

}
