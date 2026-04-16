package autosearch.proj.application.DTOs;

import java.util.Date;

public class ScrapeSummaryDTO {

	private int carsAdded;
	private double timeTaken;
	private Date completedDate;
	
	public ScrapeSummaryDTO() {}
	
	public ScrapeSummaryDTO(int carsAdded, double timeTaken, Date completedDate) {
		this.carsAdded = carsAdded;
		this.timeTaken = timeTaken;
		this.completedDate = completedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public int getCarsAdded() {
		return carsAdded;
	}

	public void setCarsAdded(int carsAdded) {
		this.carsAdded = carsAdded;
	}

	public double getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(double timeTaken) {
		this.timeTaken = timeTaken;
	}
	
	
}
