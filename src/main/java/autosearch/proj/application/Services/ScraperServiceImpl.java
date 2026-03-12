package autosearch.proj.application.Services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;

@Service
public class ScraperServiceImpl {
	// car repo object for making calls to repository
	private final CarRepository carRepo;
	private final CarService carService;
	
	Date currentDate = new Date();
	String source = "siteA.html";

	// constructor to create car Repo object
	ScraperServiceImpl(CarRepository carRepo, CarService carService) {
		this.carRepo = carRepo;
		this.carService = carService;
	}
	
	//wrapper method to call all private helper methods
	public void scrapeAndSaveSiteA() throws IOException {
		List<CarDTO> scrapedList = scrapeSiteA();
		List<CarDTO> dbDTOList = getDBCarsAsDTOs();
		
		compareForDupesAndSave(dbDTOList, scrapedList);
		
	}
	
//private helper method that scrapes the correct site, and populates the first DTO list
	//we will need
	
	private List<CarDTO> scrapeSiteA() throws IOException {

		// bucket for carDtos to be held in, list
		List<CarDTO> scrapedList = new ArrayList<>();

		
		// File object which reads the classpath and grabs the matching file
		File file = new ClassPathResource("static/siteA.html").getFile();

		// check if file exists, if doesn;t break the logic so as to not break anything

		Document doc = Jsoup.parse(file, "UTF-8");

		// create list of elements
		Elements carListings = doc.select(".car-listing");

		// loop through each element to extract their data.
		for (Element listing : carListings) {
			String year = listing.selectFirst(".year").text().replace("Year: ", "");

			String make = listing.selectFirst(".make").text().replace("Make: ", "");

			String model = listing.selectFirst(".model").text().replace("Model: ", "");

			String mileageText = listing.selectFirst(".mileage").text().replace("Mileage: ", "");
			int mileage = Integer.parseInt(mileageText);

			String priceText = listing.selectFirst(".price").text().replace("Price: ", "");
			double price = Double.parseDouble(priceText);

			// also create a DTO to populate the list we will compare against the
			CarDTO carDTO = new CarDTO(make, model, year, mileage, price);

			scrapedList.add(carDTO);

			// now convert cars to DTOs to prepare for comparison

		}

		return scrapedList;

	}

	//helper method that gets the DB entities and converts to DTO, list number 2 done.
	private List<CarDTO> getDBCarsAsDTOs() {
		List<Car> dbList = carService.returnAllCars();
		List<CarDTO> dbListAsDTOs = carService.convertToDTOList(dbList);
		return dbListAsDTOs;
		
	}

	//duplicate checking, if not present in the DB List, we can save as a new entity with 
	//proper attributes.  
	private void compareForDupesAndSave(List<CarDTO> dbListAsDTOs, List<CarDTO> scrapedList) {

		
		for(CarDTO car : scrapedList) {
			if(!dbListAsDTOs.contains(car)) {
				Car newCar = new Car(car.getMake(), car.getModel(), car.getYear(),
									car.getMileage(), car.getPrice(), source, currentDate );
				carRepo.save(newCar);
			}
		}
		
	}

}
