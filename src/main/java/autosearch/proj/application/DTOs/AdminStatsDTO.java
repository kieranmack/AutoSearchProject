package autosearch.proj.application.DTOs;

public class AdminStatsDTO {

	//DTO object for returning admin stats, count of each field. 
	private long carCount;
	private long userCount;
	private long favoriteCount;
	
	public AdminStatsDTO() {}
	
	public AdminStatsDTO(long carCount, long userCount, long favoriteCount) {
		this.carCount = carCount;
		this.userCount = userCount;
		this.favoriteCount = favoriteCount;
	}

	public long getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
}
