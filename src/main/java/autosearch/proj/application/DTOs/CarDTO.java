package autosearch.proj.application.DTOs;

//Car object with only user facing fields, hides things like id, source, and dateadded.
public class CarDTO {

	private String make;
	private String model;
	private String year;
	private int mileage;
	private double price;
	
	public CarDTO() {}
	//very simple class, will use the constructor to turn entities into these objects 
	//which will be sent to users
	public CarDTO(String make, String model, String year, int mileage, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}
	
	//G & S
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CAR DTO: " + make + " " + model + " " +
							year + " " + mileage + " " + price;
	}
	
	
	
}
