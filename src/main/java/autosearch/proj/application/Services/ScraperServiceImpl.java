package autosearch.proj.application.Services;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;

@Service
public class ScraperServiceImpl {
	//car repo object for making calls to repository
	private final CarRepository carRepo;
	
	//constructor to create car Repo object
	ScraperServiceImpl(CarRepository carRepo){
		this.carRepo = carRepo;
	}

	public void scrapeAndSaveSiteA() throws IOException {
		
		Date currentDate = new Date();
		String source = "siteA.html";
		// File object which reads the classpath and grabs the matching file
		File file = new ClassPathResource("static/siteA.html").getFile();

		// check if file exists, if doesn;t break the logic so as to not break anything
		
		Document doc = Jsoup.parse(file, "UTF-8");
		
		//create list of elements
		Elements carListings = doc.select(".car-listing");
		
		//loop through each element to extract their data. 
		for(Element listing : carListings) {
			String year = listing.selectFirst(".year").text()
					.replace("Year: ", "");
			
			String make = listing.selectFirst(".make").text()
					.replace("Make: ", "");
			
			String model = listing.selectFirst(".model").text()
				.replace("Model: " , "" );
			
			String mileageText = listing.selectFirst(".mileage").text()
					.replace("Mileage: ", "");
			int mileage = Integer.parseInt(mileageText);
			
			String priceText = listing.selectFirst(".price").text()
					.replace("Price: ", "");
			double price = Double.parseDouble(priceText);
			
			//create new car object using attributes
			Car car = new Car(make, model, year, mileage, price, source, currentDate);
			
			carRepo.save(car);
			
		}
		
	}
}
