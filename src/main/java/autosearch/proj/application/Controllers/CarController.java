package autosearch.proj.application.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Services.CarService;

//Rest API Annotation to allow for HTTP method
@RestController
public class CarController {

	// CarService object
	private final CarService carService;

	// constructor to create carservice object to make calls to the service class
	public CarController(CarService carService) {
		this.carService = carService;
	}

	// Endpoint for GET HTTP method, ie READ (CRUD), this will return all car
	// objects in
	// the database as JSON
	@GetMapping("/carsearch")
	@ResponseBody
	public List<Car> getAllCars() {
		return carService.returnAllCars();

	}

	
	}

