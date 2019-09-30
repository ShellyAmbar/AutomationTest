package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


import main.java.WatchlistTest;

class EdgeJunitTest {

	@Test
	void test() throws InterruptedException {

		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.edge.driver", projectLocation+"\\lib\\Drivers\\EdgeDriver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		//testing the program on a Edge browser
		WatchlistTest edgeBrowserTest = new WatchlistTest(driver);
	}

}
