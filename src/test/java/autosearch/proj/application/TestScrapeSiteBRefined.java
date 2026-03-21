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

public class TestScrapeSiteBRefined {

	@Autowired
	CarRepository carRepo;
	
	@Autowired
	CarService carService;
	
	@Test
	public void RefinedScrapeSiteB() throws IOException{
		File file = new ClassPathResource("static/siteB.html").getFile();
		
		String year = "";
		String make = "";
		String model = "";
		int mileage = 0;
		double price = 0;
		String source = "siteB.html";
		Date date = new Date();
		
		Document doc = Jsoup.parse(file);
		
		Elements listings = doc.select(".listing");
		
		for(Element listing : listings) {
			String title = listing.select(".title").text();
			//split up the title, now do the same for the details, which includes
			//mileage and price. 
			String[] titleSplit = title.split(" ");
			int index = 0;
			while(index < titleSplit.length) {
				if(index == 0) {
					 year = titleSplit[index];
				}else if(index == 1) {
					 make = titleSplit[index];
				}else {
					 model = titleSplit[index];
				}
				index++;
			}
			//here is where we split up details
			//I hardcoded position using detailSplit[i] since I would assume a real world
			//site would follow a distinct structure, so that's why I did it that way.  
			String details = listing.select(".details").text();
			String []detailSplit = details.split(" ");
			
			mileage = Integer.parseInt(detailSplit[1]);
			
			price = Double.parseDouble(detailSplit[4].replace("$", ""));
			
			Car car = new Car(year, make, model, mileage, price, source, date);
			
			System.out.println(car.toString());
			
			//okay, this is working, now all there is to do is create it in the 
			//service class, nice.  And add duplicate checking.  
		}
		
		
		
		
		
		
		
	}
}
