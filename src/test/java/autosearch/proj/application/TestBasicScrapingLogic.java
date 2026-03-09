package autosearch.proj.application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import autosearch.proj.application.Entities.Car;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestBasicScrapingLogic {

	@Test
	public void testScrape() throws IOException {
		
		Date currentDate = new Date();
		String source = "siteA.html";
		// File object which reads the classpath and grabs the matching file
		File file = new ClassPathResource("static/siteA.html").getFile();

		// check if file exists, if doesn;t break the logic so as to not break anything
		if (!file.exists()) {
			System.out.println("File not found.");
			return;
		}

		// parse the file for it's contents

		Document doc = Jsoup.parse(file, "UTF-8");

		// We now have content in the form of a doc obj, 
		//select the heading's text, then print
		String headerText = doc.selectFirst("h2").text();
		System.out.println("Header Text: " + headerText);
		System.out.println("///////////////////////////////\n");
		
		
		// testing to grab div using selectFirst tab
		String firstListing = doc.selectFirst("div").text();
		System.out.println("First listing (test): " + firstListing);
		System.out.println("///////////////////////////////\n");

		String firstListingHTML = doc.selectFirst("div").html();
		System.out.println("First Listing HTML: " + firstListingHTML);
		System.out.println("///////////////////////////////\n");
		// Now we will test to see if we can use for each loop using Jsoups element
		// object
		// and succesfully check through each div.

		Elements divs = doc.select("div"); 
		// Elements pulls everything that is annotated with it's respective tag, "p" or
		// "div"
		// in this case
		Elements paragraphs = doc.select("p");

		for (Element para : paragraphs) {
			System.out.println("Paragraphs: " + para.text());
		}
		System.out.println("///////////////////////////////\n");

		// success, nice all there needs to be done is to create static sites, and then
		// populate raw car objects
		for (Element div : divs) {
			System.out.println("Div: " + div.text());
		}
		System.out.println("///////////////////////////////\n");

		// testing to pull out data from each span class in HTML file
		Element testCarDiv = doc.selectFirst(".car-listing");
		System.out.println(testCarDiv);
		System.out.println("///////////////////////////////\n");
		
		//Selecting each value using the div tag, ."value" to indicate what to pull from
		String year = testCarDiv.selectFirst(".year").text()
				.replace("Year: ", "");
		
		String make = testCarDiv.selectFirst(".make").text()
				.replace("Make: ", "");
		
		String model = testCarDiv.selectFirst(".model").text()
			.replace("Model: " , "" );
		
		String mileageText = testCarDiv.selectFirst(".mileage").text()
				.replace("Mileage: ", "");
		int mileage = Integer.parseInt(mileageText);
		
		String priceText = testCarDiv.selectFirst(".price").text()
				.replace("Price: ", "");
		double price = Double.parseDouble(priceText);
		
		Car car = new Car(make, model, year, mileage, price, source, currentDate);
		
		System.out.println("Cars Attributes: " + make + "\n" + model + "\n"  + year + "\n" 
						+ mileage + "\n" + price + "\n" + source + "\n"  + currentDate );
		
		System.out.println("///////////////////////////////\n");
		
		//I will now duplicate it exactly, but try to loop through to populate all listings 
		//on the HTML
		System.out.println("THIS IS WHERE I AM LOOPING THROUGH EVERY ELEMENT\n");
		Elements carListings = doc.select(".car-listing");
		
		for(Element listing : carListings) {
			String year1 = listing.selectFirst(".year").text();
			String make1 = listing.selectFirst(".make").text();
			String model1 = listing.selectFirst(".model").text();
			String mileageText1 = listing.selectFirst(".mileage").text()
					.replace("Mileage: ", "");
			int mileage1 = Integer.parseInt(mileageText1);
			
			String priceText1 = listing.select(".price").text()
					.replace("Price: ", "");
				
			
			double price1 = Double.parseDouble(priceText1);
			
			Car car1 = new Car(make1, model1, year1, mileage1, price1, source, currentDate);
			
			System.out.println("Cars Attributes: " + make1 + "\n" + model1 + "\n"  + year1 + "\n" 
							+ mileage1 + "\n" + price1 + "\n" + source + "\n"  + currentDate );
			
			System.out.println("///////////////////////////////\n");
			
		}
		//nevermind it's working, just had to fix formatting in my div elements, now all 
		//I really need to do is implement this logic saving to the database, way ahead of schedule. 

	}
}
