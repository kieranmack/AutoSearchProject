package autosearch.proj.application;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.jpa.domain.Specification;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Services.CarService;

//Creates temporary in memory database for testing
@DataJpaTest

public class TestCarSearch {

	@Autowired
	CarRepository carRepo;
	
	
	
	//before each test class is run, this setup function will be called to populate in 
	//memory database. 
	@BeforeEach
	void setup() {
		Date date = new Date();
		Car car = new Car("Toyota", "Camry", "2011", 46000, 500.00, "siteB.html", 
				date);
		carRepo.save(car);
		Car car2 = new Car("Hyundai", "Elantra", "2020", 23000, 16000.00, "siteB.html", 
				date);
		carRepo.save(car2);
		
	}
	
	@Test
	void findUsers() {
		List<Car> cars = carRepo.findAll();
		System.out.println(cars.toString());
		assert cars.size() == 2;
	}
	
	
	
	@Test
	void findOnSpecifications(String make, String model, String year, Integer mileage,
				Double price) {
		Specification<Car> spec = Specification.where(spec = null);
		
		//specifications to build dynamic query, if it's not null, will be added to the 
		//where clause when Hibernate generates a query, otherwise it will be left out. 
		if (make != null) {  
		    spec = spec.and((root, query, cb) -> cb.equal(root.get("make"), make));  
		  }  
		
		if (model != null) {  
		    spec = spec.and((root, query, cb) -> cb.equal(root.get("model"), model));  
		  }  
		
		if (year != null) {  
		    spec = spec.and((root, query, cb) -> cb.equal(root.get("year"), year)); 
		  }  
		
		if (mileage != null) {  
		    spec = spec.and((root, query, cb) -> cb.equal(root.get("mileage"), mileage));  
		  }  
		
		if (price != null) {  
		    spec = spec.and((root, query, cb) -> cb.equal(root.get("price"), price));  
		  }  
		
	}
	
	
}
