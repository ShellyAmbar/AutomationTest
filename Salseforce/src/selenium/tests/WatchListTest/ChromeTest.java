package selenium.tests.WatchListTest;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import Properties.WatchListTestProperties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ChromeTest {
	
	private static WebDriver driver = null;
	private static String webTitle="";
	private static double rating = 0;
	private static List<String> listOfShows = null;
	private static String userInput="";
	private static WatchListTestProperties watchListTestProperties;
	private static WebElement addToListBtn = null;
	private static WebElement searchBar=null;
	private static WebElement ratingElement = null;
	private static WebElement signInButtn = null;
	private static List<String> ListOfRatedShows =null;
	private static Actions action = null;
	private static boolean isAllShowsInList = true;
	private static String webUrl="";
	
	public static void main(String[] args) throws InterruptedException {
		
		//generate new properties to test and set them to local properties
		watchListTestProperties = new WatchListTestProperties();
		listOfShows = watchListTestProperties.getListOfShows();
		rating = watchListTestProperties.getRating();
		ListOfRatedShows = new ArrayList<String>();
		
		//settings for chrome driver and open the website
		SetDriverAndOpenWebsite();
			
		//check the title of the web
		CheckSiteUrl();
		
		action = new Actions(driver);
	// first we need the user  to sign in IMDB site (I am  providing my details for the test -> automatically)
		SignInUserToSite();
		//looping trough the given list of shows and if the rating of the show is matching the rating that has been given, it will add it to the "Watch List".
		AddShowsToWatchListByRating();

	// navigating to the watch list page and check if the watch list is correct	
		
		CheckCorrectnessOfWatchList();
		
		 driver.close();
		 driver.quit();

	}
	
	private static void AddShowsToWatchListByRating() throws InterruptedException {
		
		for(int i=0; i<listOfShows.size();i++) {
			
			System.out.println("searching for the name :"+ listOfShows.get(i));
			searchBar = driver.findElement(By.cssSelector("#navbar-query"));
			searchBar.sendKeys(listOfShows.get(i));
			
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("#navbar-suggestionsearch > div:nth-child(1) > a"))
		    .click();
			Thread.sleep(1000);
			
			ratingElement = driver.findElement(By.cssSelector(".ratingValue > strong:nth-child(1) > span:nth-child(1)"));
			if(Double.parseDouble(ratingElement.getText().toString())>= rating) {
				addToListBtn = driver.findElement(By.cssSelector("div.ribbonize > div:nth-child(1)"));
				addToListBtn.click();
				//add the show to the new list
				ListOfRatedShows.add(listOfShows.get(i));
				System.out.println("added show:" + listOfShows.get(i)  +" to watchlist.");
				Thread.sleep(1000);
			}
		}
	}
	
	private static void CheckSiteUrl() {
		webTitle = driver.getCurrentUrl();
		if(webTitle.equalsIgnoreCase("https://www.imdb.com/")) {
			System.out.println("The web title is matching.");
		}
		else {
			System.out.println("The web title:" + webTitle +"is not matching: imdb.com.");
		}
		
	}
	
	private static void SignInUserToSite() throws InterruptedException {
        driver.findElement(By.cssSelector("#imdb-signin-link > span")).click();
		
		Thread.sleep(1000);
		//click on the imdb signin option
		driver.findElement(By.cssSelector("#signin-options > div > div:nth-child(2) > a:nth-child(1) > span.auth-provider-text")).click();
		Thread.sleep(1000);
		//entering my details for the account on imdb
		
		driver.findElement(By.cssSelector("#ap_email")).sendKeys("ambarshely@gmail.com");
		driver.findElement(By.cssSelector("#ap_password")).sendKeys("metukonet101");
		driver.findElement(By.cssSelector("#signInSubmit")).click();
	
		
		
	}
	
	private static void GetWatchlistAndRatingFromUser() {

		//set the minimum rating
		System.out.println("Please enter the minimum rating number:");
		rating = Integer.parseInt(System.console().readLine());
		//set the list of movies
		listOfShows = new ArrayList<String>();
		System.out.println("Enter movie name or 'done' to continue.");
		while(!userInput.toString().equalsIgnoreCase("done")) 
		{
			userInput = System.console().readLine();
			listOfShows.add(userInput.toString());
			System.out.println("Enter movie name or 'done' to continue.");
		}	
	}
	
	private static void CheckCorrectnessOfWatchList() throws InterruptedException {
		
		System.out.println("navigating to the watch list page");
		
		driver.findElement(By.cssSelector(".watchlist > a:nth-child(1)")).click();
		//clicking to navigate to the Watch List page
		
		Thread.sleep(1000);
		
		//now we will check if all the shows in the watchlist have a rating of above of the rating we choosed.
		// Check if all the correct titles are in the watch list.
		CheckCorrectnessBetweenTitlesOfLists();
		// Check if all the ratings in the watch list are correct.
		CheckCorrectnessOfWatchListRatings();
		
		 if(isAllShowsInList==false) {
			 System.out.println("The test failed to set all the rated shows in the list");
		 }else if(isAllShowsInList==true) {
			 System.out.println("The test succedded.");
		 }
	}
	
	private static void CheckCorrectnessBetweenTitlesOfLists() {
		
		int currentIndex = 0;
		String currentTitle = "";
		
		List<WebElement> listOfTitlesElements = driver.findElements(By.className("lister-item-header"));
		for(WebElement titleElement : listOfTitlesElements) {
			currentTitle = titleElement.getText().toString().toLowerCase();
			
			if(currentTitle.equals(ListOfRatedShows.get(currentIndex++).toLowerCase())) {
				System.out.println("found show:" + currentTitle +" that match the rated list.");
			}else {
				isAllShowsInList = false;
				System.out.println("found unmatched show - "+currentTitle+" in the watchlist, to the given show : "+ListOfRatedShows.get(currentIndex-1).toLowerCase());
				
			}
		}
	}
	
	private static void CheckCorrectnessOfWatchListRatings() {
		
		double currenRating = 0;
		List<WebElement> listOfRatingElements = driver.findElements(By.className("ratings-imdb-rating"));
		for(WebElement ratingElement : listOfRatingElements) {
			currenRating = Double.parseDouble(ratingElement.getText().toString());
			if(currenRating< rating) {
				isAllShowsInList = false;
				System.out.println("found unmatched rating in the watchlist.");
			}else {
				System.out.println("found rating :" + currenRating +" that match the rated list.");
			}
		}
	}

	private static void SetDriverAndOpenWebsite() {
		
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation+"\\lib\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Creating new server");
		//max time of waiting to browser to show up
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().to("https://www.imdb.com/");
		System.out.println("Navigate to the web");
		driver.manage().window().maximize();
	}
}
