import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class LaunchApplication {
	//Testing if Webdriver is configured correctly

	public static void main(String[] args) {
		// Change webdriver filepath to your own manually (Easy solution now in the start up phase)
		System.setProperty("webdriver.chrome.driver", "/Users/Gustaf/Desktop/SeleniumDrivers/chromedriver");
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8080/");
		

	}

}
