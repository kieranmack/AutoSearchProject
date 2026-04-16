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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.DTOs.ScrapeSummaryDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;

@Service
public class ScraperServiceImpl {
	// car repo object for making calls to repository
	private final CarRepository carRepo;
	private final CarService carService;

	Date currentDate = new Date();
	String sourceA = "siteA.html";
	String sourceB = "siteB.html";

	// constructor to create car Repo object
	ScraperServiceImpl(CarRepository carRepo, CarService carService) {
		this.carRepo = carRepo;
		this.carService = carService;
	}

	// wrapper method to call all private helper methods
	@Scheduled(cron = "0 0 0 * * *")
	public ScrapeSummaryDTO scrapeAndSaveSite() throws IOException {
		double startTime = System.nanoTime();
		List<CarDTO> scrapedListA = scrapeSiteA();
		List<CarDTO> scrapedListB = scrapeSiteB();
		List<CarDTO> scrapedList = mergeLists(scrapedListA, scrapedListB);
		List<CarDTO> dbDTOList = getDBCarsAsDTOs();
		int carsAdded = compareForDupesAndSave(dbDTOList, scrapedList);
		
		//end time to calculate method execution time
		double endTime = System.nanoTime();
		
		//convert to seconds
		double allotedTime = (endTime - startTime) / 1000000000.0;
		Date completedDate = new Date();
		return new ScrapeSummaryDTO(carsAdded, allotedTime, completedDate);
		
		

	}

//private helper method that scrapes the correct site, and populates the first DTO list
	// we will need

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
			CarDTO carDTO = new CarDTO(make, model, year, mileage, price, sourceA);

			scrapedList.add(carDTO);

			// now convert cars to DTOs to prepare for comparison

		}

		return scrapedList;

	}

	// helper method that gets the DB entities and converts to DTO, list number 2
	// done.
	private List<CarDTO> getDBCarsAsDTOs() {
		List<Car> dbList = carService.returnAllCars();
		List<CarDTO> dbListAsDTOs = carService.convertToDTOList(dbList);
		return dbListAsDTOs;

	}

	// duplicate checking, if not present in the DB List, we can save as a new
	// entity with
	// proper attributes.
	private int compareForDupesAndSave(List<CarDTO> dbListAsDTOs, List<CarDTO> scrapedList) {

		int carCount = 0;
		List<CarDTO> savedCars = new ArrayList<>();
		for (CarDTO car : scrapedList) {
			if (!dbListAsDTOs.contains(car)) {
				Car newCar = new Car(car.getMake(), car.getModel(), car.getYear(), car.getMileage(), car.getPrice(),
						car.getSource(), currentDate);
				carCount++;
				carRepo.save(newCar);
				savedCars.add(car);
				
			}
		}

		return carCount;
	}

	// Here is where we create the first list for the scraped cars from siteB
	private List<CarDTO> scrapeSiteB() throws IOException {

		List<CarDTO> scrapedList = new ArrayList<>();
		File file = new ClassPathResource("static/siteB.html").getFile();

		String year = "";
		String make = "";
		String model = "";
		int mileage = 0;
		double price = 0;

		Document doc = Jsoup.parse(file, "UTF-8");

		Elements listings = doc.select(".listing");

		for (Element listing : listings) {
			String title = listing.select(".title").text();
			// split up the title, now do the same for the details, which includes
			// mileage and price.
			String[] titleSplit = title.split(" ");
			int index = 0;
			while (index < titleSplit.length) {
				if (index == 0) {
					year = titleSplit[index];
				} else if (index == 1) {
					make = titleSplit[index];
				} else {
					model = titleSplit[index];
				}
				index++;
			}
			// here is where we split up details
			// I hardcoded position using detailSplit[i] since I would assume a real world
			// site would follow a distinct structure, so that's why I did it that way.
			String details = listing.select(".details").text();
			String[] detailSplit = details.split(" ");

			mileage = Integer.parseInt(detailSplit[1]);

			price = Double.parseDouble(detailSplit[4].replace("$", ""));

			CarDTO car = new CarDTO(make, model, year, mileage, price, sourceB);
			scrapedList.add(car);
		}

		return scrapedList;

	}

	// merge the two scraped lists together so as to not rewrite an entire new
	// dupecheck
	// method
	private List<CarDTO> mergeLists(List<CarDTO> scrapedA, List<CarDTO> scrapedB) {
		for (CarDTO car : scrapedB) {
			if (!scrapedA.contains(car)) {
				scrapedA.add(car);
			}
		}
		return scrapedA;
	}

}
