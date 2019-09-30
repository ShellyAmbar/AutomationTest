package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import main.java.WatchlistTest;

class ChromeJunitTest {

	@Test
	void test() throws InterruptedException {
		
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation+"\\lib\\Drivers\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//testing the program on a Chrome browser
		WatchlistTest chromeBrowserTest = new WatchlistTest(driver);
		
		
	}

}
