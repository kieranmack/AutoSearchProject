package autosearch.proj.application.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Custom Object Car

//Entity and table tag to indicate it is a mapped entity of the database, table self explanatory
@Entity
@Table(name = "cars")
public class Car {

	//Attributes, one to one with the database entity, generatedvalue supplies a PK to the
	//value, generated value ensures that when a new entity is created, it is supplied with a 
	//PK that lines up with the MySQL database. 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cars_id")
	private int carsId;
	
	private String make;
	private String model;
	
	//Added annotation, because YEAR treated as a keyword in testing. Breaks the system. 
	@Column(name = "year")
	private String year;
	private int mileage;
	private double price;
	private String source;
	private Date dateAdded;
	
	public Car() {}
	
	public Car (String make, String model, String year, int mileage, double price, String source, 
			Date dateAdded){
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
		this.source = source;
		this.dateAdded = dateAdded;
	}
	

	
	//Getters and Setters
	//Left out setId, because the the GeneratedValue tag sets it automatically when mapped. 
	//Scraping service will use setters to generate raw car object down the line, repository will
	//save it, generate a consistent ID value and insert to the database. 
	public int getId() {
		return carsId;
	}
	
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
	public String getSource() {
		return source;
	}
	public void setUrl(String source) {
		this.source = source;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	@Override
	public String toString() {
		return "CAR: " + make + " " + model + " " +
							year + " " + mileage + " " + price + " " + source
									+ " " + dateAdded;
	}
	
	
}
