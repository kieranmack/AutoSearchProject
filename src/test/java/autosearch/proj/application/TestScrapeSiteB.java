package autosearch.proj.application;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Services.CarService;

public class TestScrapeSiteB {

	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private CarService carService;
	
	@Test
	public void TestScrapeSiteB() throws IOException {
		File file = new ClassPathResource("static/siteB.html").getFile();
		
		String source = "siteB.html";
		Date date = new Date();
		
		Document doc = Jsoup.parse(file);
		
		String firstDiv = doc.selectFirst("div").text();
		
		System.out.println(firstDiv);
		System.out.println("//////////////////////////////////////////////////");
		
		Elements carListings = doc.select(".listing");
		
		for(Element car : carListings) {
			System.out.println(car.toString());
		}
		
		System.out.println("//////////////////////////////////////////////");
		
		Element listingElem = doc.selectFirst(".listing");
		String listing1 = doc.selectFirst(".listing").text();
		
		System.out.println(listing1);	
		
		System.out.println("/////////////////////////////////////////////");
		
		for(Element listing : carListings) {
			String title = listing.select(".title").text();
			System.out.println(title);
		}
		System.out.println("//////////////////////////////////////////////");
		//Basic extraction done understandable, now need to split title string into three
		//distinct string for year, make, model, 
		
		String title = listingElem.select(".title").text();
		
		String [] result = title.split(" ");
		//worked, split the string by the spaces in between to have three distinct strings
		//now need to populate year, make and model from that.  
		for(String str : result) {
			System.out.println(str);
		}
		
		//making while loop, if index is certain posiition, since it will be 
		//the same position index for each "result" array.  
		/*
		String year = "";
		String make = "";
		String model = "";
		int index = 0;
		while(index < 3) {
			if(index == 0) {
				 year = result[index];
			}else if(index == 1) {
				 make = result[index];
			}else {
				 model = result[index];
			}
			index++;
		}
		
		
		System.out.println("Year: " + year + " Make: " + make + " Model: " + model );
		*/
		
		//worked! nice, now I need to expand this to make it reusable across all elements, 
		//will probably be a separate method in my scraper service, but we will try to cram it
		//for now in test class
	
		/*
		System.out.println("/////////////////////////////////////////////////////\n");
		System.out.println("Here is where we will test populating car objects from each"
				+ "element ");
		
		for(Element elem : listingElem) {
			String [] splitStrings = elem.select(".title").text().split(" ");
			
			//split string and populate each field
			String year = "";
			String make = "";
			String model = "";
			int index = 0;
			while(index < splitStrings.length) {
				if(index == 0) {
					 year = splitStrings[index];
				}else if(index == 1) {
					 make = splitStrings[index];
				}else {
					 model = splitStrings[index];
				}
				index++;
			}
			
			//straight forward as before, use replace to get rid of unwanted details
			String details = elem.select(".details").text().replace("Mileage: ", "");
			
			String [] splitDetails 
			
			*/
		
		//on second thought need to also split the details class, since they
		//are annotated with the same class. 
		String details = listingElem.select(".details").text();
		
		System.out.println("///////////////////////////////\n" + details);
		
		String []splitDetails = details.split(" ");
		for(String detail : splitDetails) {
			System.out.println(detail);
			}
		
		int mileage = Integer.parseInt(splitDetails[1]);
		
		double price = Double.parseDouble(splitDetails[4].replace("$", ""));
		
		System.out.println("Mileage: " + mileage + " Price: " + price);
			
		//okay this worked, I am going to create a new test class to try and combine both of these
		//split methods into one method which we can reuse for the entire HTML structure. 
		}
		
	}

