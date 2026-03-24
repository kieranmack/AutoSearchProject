package autosearch.proj.application;

import org.springframework.beans.factory.annotation.Autowired;

import autosearch.proj.application.Repositories.CarRepository;
import autosearch.proj.application.Services.CarService;

public class TestCarSearch {

	@Autowired
	CarRepository carRepo;
	
	@Autowired
	CarService carService;
	
	
}
