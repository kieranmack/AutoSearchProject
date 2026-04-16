package autosearch.proj.application.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.AdminStatsDTO;
import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Repositories.FavoriteRepository;
import autosearch.proj.application.Repositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;

	//returning counts of each dB type 
	 public AdminStatsDTO returnStats() {
		return new AdminStatsDTO(carRepository.count(), userRepository.count(), 
				favoriteRepository.count());
		
	}
	
	
}
