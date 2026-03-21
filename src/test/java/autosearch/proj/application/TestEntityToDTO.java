package autosearch.proj.application;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Services.CarService;
import autosearch.proj.application.Services.CarServiceImpl;

@SpringBootTest
public class TestEntityToDTO {

	@Autowired
	private CarRepository carRepo;
	List<CarDTO> dtoList = new ArrayList<>();
	
	@Autowired
	private CarServiceImpl carService;
	
	
	
	//GOAL:
	
	//Pull entity objects, and then populate a new list with the DTO objects, only with 
	//proper fields showing
	@Test
	public void convertToCarDTO() {
		
		
		List<Car> entityList = carService.returnAllCars();
		
		for(Car car : entityList) {
			
			CarDTO transferCar = new CarDTO(car.getMake(), car.getModel(), car.getYear(),
										car.getMileage(), car.getPrice(), car.getSource());
			
			dtoList.add(transferCar);
		}
		
		for(CarDTO newCar : dtoList) {
			System.out.println(newCar.toString());
		}
		
	}
}

//Working as intended, just what we need for testing the duplicate logic
