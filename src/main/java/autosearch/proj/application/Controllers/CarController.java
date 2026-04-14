package autosearch.proj.application.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import autosearch.proj.application.ApiResponse.ApiResponse;
import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Services.CarService;
import jakarta.servlet.http.HttpSession;

//Rest API Annotation to allow for HTTP method
@RestController
public class CarController {

	@Autowired
	private CarService carService;

	// constructor to create carservice object to make calls to the service class
	
	// Endpoint for GET HTTP method, ie READ (CRUD), this will return all car
	// objects in
	// the database as JSON
	@GetMapping("/admin/")
	@ResponseBody
	public List<Car> getAllCars() {
		return carService.returnAllCars();

		}


	//Controller for car search, this is the endpoint which we wil check against the parameters
	//each parameter is optional meaning, if the user leaves it out it will not be added to where clause
	//detailed code in service class
	@GetMapping("/api/search/")
	@ResponseBody
	public List<CarDTO> getCars(
			@RequestParam(required = false) String make,
			@RequestParam(required = false) String model,
			@RequestParam(required = false) String minYear,
			@RequestParam(required = false) String maxYear,
			@RequestParam(required = false) Integer minMileage,
			@RequestParam(required = false) Integer maxMileage,
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice){
		
		return carService.findCars(make, model, minYear, maxYear, minMileage, maxMileage,
						minPrice, maxPrice);
	}
	@GetMapping("/api/search/favorites/")
	@ResponseBody
	public List<CarDTO> getFavorites(HttpSession session){
		return carService.returnFavorites(session);
	}
	
	@GetMapping("/api/makes/")
	@ResponseBody
	public List<String> getMakes(){
		return carService.returnMakes();
	}
	
	@GetMapping("/api/models/")
	@ResponseBody
	public List<String> getModelsByMake(@RequestParam(required = true) String make){
		return carService.returnModelsByMake(make);
	}
	
	@PostMapping("api/addFavorite")
	@ResponseBody
	public ApiResponse addFavorite( @RequestParam(required = true)
											int carId, HttpSession session) {
		return carService.addFavorite(session, carId);
	}
	
	
	
}
