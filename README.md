# AutomationTest
An Automation test using Junit & Selenium WebDriver in java on Eclipse environment for testing 3 cases on the IMDB website : 
case 1 - run the program on Chrom browser
case 2 - run the program on Edge browser
case 3 - run the program on Firefox browser

The program itself gets a list of movie's titles and a minimum rating number from a text file, and then :
1) signin the user to the IMDB site.
2) searching each of the title and checking it's rating number 
- if it is higher or equal to the number we gave - it add it to the watch list.
3) check the watchlist's movies -  if all of them are the movies we provided and their rating number matchs
- the program will print "succsseded".


