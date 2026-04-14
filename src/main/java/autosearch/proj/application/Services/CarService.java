package autosearch.proj.application.Services;
import java.util.List;


import autosearch.proj.application.ApiResponse.ApiResponse;
import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;
import jakarta.servlet.http.HttpSession;

//Interface for Car Service Implementation Class, I will include these for each 
//Service class, as to ensure cleanliness.  Right now only one method, just to show the 
//Structure works. 

public interface CarService {

	
	public List<Car> returnAllCars();
	
	public Car createCar(Car car);
	
	public CarDTO convertToDTO(Car car);
	
	public List<CarDTO> convertToDTOList(List<Car> entityList);
	
	public List<CarDTO> findCars(String make, String model, String minYear, String maxYear,
			Integer minMileage, Integer maxMileage, Double minPrice, Double maxPrice);
	
	public List<CarDTO> returnFavorites(HttpSession session);
	
	public List<CarDTO> returnAllCarDTOs();
	
	public List<String> returnMakes();
	
	public List<String> returnModelsByMake(String make);
	
	public ApiResponse addFavorite(HttpSession session, int carId);
	
	
	
	
	
}
