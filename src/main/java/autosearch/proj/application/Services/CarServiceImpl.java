package autosearch.proj.application.Services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Entities.Favorite;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Repositories.FavoriteRepository;
import jakarta.servlet.http.HttpSession;

//Car Service implementation, override interface methods
//Service tag is necessary for Spring Boot to read
@Service
public class CarServiceImpl implements CarService {

	//Create instance of repository in service for calls to it's methods. (JPA's methods)
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	//Method to return all cars, this will likely be used sparingly in my final 
	//project, perhaps for the admins screen, but just for now to show basic functionality
	@Override
	public List<Car> returnAllCars() {
		
		return carRepository.findAll();
	}
	
	//Create car method
	
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
	
	//method to return cars based on session which holds user Id
	@Override
	public List<CarDTO> returnFavorites(HttpSession session){
		User user = (User)session.getAttribute("loggedIn");
		List<Favorite> favoriteList = new ArrayList<>();
		List<CarDTO> returnList = new ArrayList<>();
		
		if(user == null) {
			return returnList;
		}
		favoriteList = favoriteRepository.findByUserId(user.getId());
		
		for(Favorite fav : favoriteList) {
			CarDTO addCar = convertToDTO(fav.getCar());
			returnList.add(addCar);
		}
		
		return returnList;
		
		
	}
	
	//dynamic search method, one method should work for all searches. 
	@Override
	public List<CarDTO> findCars(String make, String model, String minYear, String maxYear,
			Integer minMileage, Integer maxMileage, Double minPrice, Double maxPrice){
		//unnrestricted allows for unfiltered usage of parameterization, tried using
		//where() but has since been deprecated. 
		Specification<Car> spec = Specification.unrestricted();
		 List<CarDTO> returnList = new ArrayList<>();
		 List<Car> dbList = new ArrayList<>();
		 //build specifications based on values given in parameter, don't have to be 
		 //present in order to build where clause. 
		 if (make != null) {  
			    spec = spec.and((root, query, cb) -> cb.equal(root.get("make"), make));  
			  }  
			
			if (model != null) {  
			    spec = spec.and((root, query, cb) -> cb.equal(root.get("model"), model));  
			  }  
			
			if (minYear != null) {  
			    spec = spec.and((root, query, cb) ->
			    cb.greaterThanOrEqualTo(root.get("year"), minYear)); 
			  }  
			
			if (maxYear != null) {  
			    spec = spec.and((root, query, cb) ->
			    cb.lessThanOrEqualTo(root.get("year"), maxYear)); 
			  }  
			
			if (minMileage != null) {  
			    spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("mileage"), minMileage));  
			  }  
			
			if(maxMileage != null) {
				spec = spec.and((root,query,cb) -> cb.lessThanOrEqualTo(root.get("mileage"), maxMileage));
			}
			
			if (minPrice != null) {  
			    spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));  
			  } 
			
			if(maxPrice != null) {
				spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"),maxPrice));
			}
			
			dbList = carRepository.findAll(spec);
			
			returnList = convertToDTOList(dbList);
			
			return returnList;
			
		 
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
	
	public List<CarDTO> returnAllCarDTOs(){
		return convertToDTOList(carRepository.findAll());
		
	}
	
	public List<String> returnMakes(){
		return carRepository.findDistinctMake();
	}
	
	
	
	
	

}
