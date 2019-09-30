package main.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WatchListTestProperties {
	private static double rating =0 ;
	private static List<String> listOfShows =null;
	public  WatchListTestProperties(){
		
		SetlistOfShows();
		//SetGenericList();
	}
	
	private void SetGenericList(){
		listOfShows = new ArrayList<String>();
		listOfShows.add("The Sopranos");
		listOfShows.add("Planet Earth II");
		listOfShows.add("Band of Brothers");
		
	}
	
	private void SetlistOfShows() {
		String projectLocation = System.getProperty("user.dir");
		listOfShows = new ArrayList<String>();
		try(BufferedReader in = new BufferedReader(new FileReader(projectLocation+"\\src\\main\\properties\\text.txt"))) {
		    String str;
		    while ((str = in.readLine()) != null) {
		    	if(!str.contains("ratings")) {
		    		String[] sentence = str.split(":");
		    		List<String> splitedSentence = new ArrayList<String>();
		    		splitedSentence.add("");
		    		splitedSentence.add("");
		    		
		    		for(int i=0; i<sentence.length;i++){
		    			splitedSentence.add(i,sentence[i]);
		    		}
		    		String[] titles = splitedSentence.get(1).split(",");
		    		for(int i=0 ; i<titles.length;i++) {
		    			listOfShows.add(titles[i].toString());
		    			System.out.println(titles[i].toString().toLowerCase());
		    		}
		    	}else if(str.contains("ratings")){
		    		
		    		String[] strings = str.split(":");
		    		rating = Double.parseDouble(strings[1]);
		    		System.out.println(rating);
		    	}
		    }
		}
		catch (IOException e) {
		    System.out.println("File Read Error");
		}
		
	}
	
	public static double getRating() {
		return rating;
	}
	public static void setRating(double rating) {
		WatchListTestProperties.rating = rating;
	}
	public static List<String> getListOfShows() {
		return listOfShows;
	}

	
	
	
	
	

}
