package autosearch.proj.application.Services;
import java.util.List;

import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.Entities.Car;

//Interface for Car Service Implementation Class, I will include these for each 
//Service class, as to ensure cleanliness.  Right now only one method, just to show the 
//Structure works. 

public interface CarService {

	
	public List<Car> returnAllCars();
	
	public Car createCar(Car car);
	
	public CarDTO convertToDTO(Car car);
	
	
}
