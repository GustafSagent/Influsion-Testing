import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class helpFunctions {

	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void login(String username, String password, WebDriver browser) throws InterruptedException {
		
		
		browser.findElement(By.cssSelector("[placeholder='Username']")).sendKeys(username);
		browser.findElement(By.cssSelector("[placeholder='Password']")).sendKeys(password);
		browser.findElement(By.xpath("//button[contains(text(),'Lets go into the wilderness!')]"));
		Thread.sleep(1000);
	}
	
}
