package autosearch.proj.application.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;

//Car Service implementation, override interface methods
//Service tag is necessary for Spring Boot to read
@Service
public class CarServiceImpl implements CarService {

	//Create instance of repository in service for calls to it's methods. (JPA's methods)
	private final CarRepository carRepository;
	
	//Constructor for repostiory object
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	//Method to return all cars, this will likely be used sparingly in my final 
	//project, perhaps for the admins screen, but just for now to show basic functionality
	@Override
	public List<Car> returnAllCars() {
		
		return carRepository.findAll();
	}
	
	//Create car method, scraper will eventually use this method to instantiate car objects
	
	public Car createCar(Car car) {
		return carRepository.save(car);
	}

}
