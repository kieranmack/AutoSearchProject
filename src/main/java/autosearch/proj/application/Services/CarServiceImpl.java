package autosearch.proj.application.Services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Repositories.CarRepository;

//Car Service implementation, override interface methods
//Service tag is necessary for Spring Boot to read
@Service
public class CarServiceImpl implements CarService {

	//Create instance of repository in service for calls to it's methods. (JPA's methods)
	@Autowired
	private CarRepository carRepository;
	
	
	
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

	//method for converting single Car element to DTO
	@Override
	public CarDTO convertToDTO(Car car) {
		CarDTO returnCar = new CarDTO(
				car.getMake(),
				car.getModel(),
				car.getYear(),
				car.getMileage(),
				car.getPrice(),
				car.getSource());
		return returnCar;
				
	}
	
	
	
	//converts entire car list into a dto list for comparison
	public List<CarDTO> convertToDTOList(List<Car> entityList){
		List<CarDTO> returnList = new ArrayList<>();
		for(Car car : entityList) {
			CarDTO newCar = convertToDTO(car);
			returnList.add(newCar);
		}
		return returnList;
	}
	
	
	
	
	

}
