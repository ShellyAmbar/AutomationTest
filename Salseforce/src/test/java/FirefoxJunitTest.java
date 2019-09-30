package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import main.java.WatchlistTest;

class FirefoxJunitTest {

	@Test
	void test() throws InterruptedException {

		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projectLocation+"\\lib\\Drivers\\FirefoxGeckoDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//testing the program on a Firefox browser
		WatchlistTest firefoxBrowserTest = new WatchlistTest(driver);
	}

}
