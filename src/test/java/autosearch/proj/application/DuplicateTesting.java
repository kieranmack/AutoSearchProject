package autosearch.proj.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Services.CarService;
import autosearch.proj.application.Services.CarServiceImpl;

import org.springframework.core.io.ClassPathResource;

@SpringBootTest
public class DuplicateTesting {

	@Autowired
	private CarServiceImpl carService;
	
	List<CarDTO> scrapedElementList;
	List<Car> dbList;
	List<CarDTO> newDbList;
	

	//ensures that the test works as intended, without this annotation
	//things break, this ensures that these are instantiated before each test is ran in Junit
	//fresh slate for each test essentially 
    @BeforeEach
    public void setup() {
       
         dbList = carService.returnAllCars();
       scrapedElementList = new ArrayList<>();
       newDbList = new ArrayList<>();
    }

	
	
	//GOAL: to grab list of database entities, convert them to DTOS, then 
	//list of new scraped objects, then compare them for any duplicates.  
	
	
	//same logic as before just scrape as normal
	@Test
	public void scrapeAndPopulateList() throws IOException {
		
		
		Date currentDate = new Date();
		String source = "siteA.html";
		
		File file = new ClassPathResource("/static/siteA.html").getFile();
		
		Document doc = Jsoup.parse(file, "UTF-8");
		
		Elements carListings = doc.select(".car-listing");
		
		for(Element listing : carListings) {
			String year = listing.select(".year").text().replace("Year: ", "");
			String make = listing.select(".make").text().replace("Make: ", "");
			String model = listing.select(".model").text().replace("Model: ", "");
			String mileageText = listing.select(".mileage").text().replace("Mileage: ", "");
			int mileage = Integer.parseInt(mileageText);
			String priceText = listing.select(".price").text().replace("Price: ", "");
			double price = Double.parseDouble(priceText);
			
			CarDTO car = new CarDTO(make, model, year, mileage, price);
			
			scrapedElementList.add(car);
			
			
		}
		 //print out DTO elements, should be properly populated as CarDTOs, check
			for(CarDTO car : scrapedElementList) {
				System.out.println(car.toString());
			}
			
			System.out.println("///////////////////////////////////");
			
			//Now need to convert dbList to DTOS
			
			for(Car car : dbList) {
				CarDTO newCar = carService.convertToDTO(car);
				newDbList.add(newCar);
			}
			
			//printing database entities mapped to new DTOs for comparison
			for(CarDTO car : newDbList) {
				System.out.println(car.toString());
			}
			
			//Success, now need to compare each of them to see if there are duplicates, should be three
			
			int dupeCount = 0;
			for(CarDTO car : scrapedElementList) {
				if(newDbList.contains(car)) {
					dupeCount++;
				}
			}
			
			System.out.println("Duplicate count: " + dupeCount);
			
			//need to override Object contains () method likely, duplicates not being 
			//registered.  Everything is being converted to DTOs however.
			
			//overridden methods allow for duplicate to work exactly as intended, scrape 
			//logic pretty much complete!
			
			
		
		
	}
	
	
	
}
