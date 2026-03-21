package autosearch.proj.application.DTOs;

import java.util.Objects;

//Car object with only user facing fields, hides things like id, source, and dateadded.
public class CarDTO {

	private String make;
	private String model;
	private String year;
	private int mileage;
	private double price;
	private String source;
	
	public CarDTO() {}
	//very simple class, will use the constructor to turn entities into these objects 
	//which will be sent to users
	public CarDTO(String make, String model, String year, int mileage, double price, 
			String source) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
							year + " " + mileage + " " + price + " " + source;
	}
	@Override
	public int hashCode() {
		return Objects.hash(make, mileage, model, price, source, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDTO other = (CarDTO) obj;
		return Objects.equals(make, other.make) && mileage == other.mileage && Objects.equals(model, other.model)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(source, other.source) && Objects.equals(year, other.year);
	}
	
	
	//Overridden hash code and equals methods, to compare VALUE and not identity
	//found out that eclipse can generate these for you automatically, so duplicate should 
	//work now in test class
	
	
	
	
	
}
