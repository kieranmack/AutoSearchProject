package autosearch.proj.application.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.DTOs.AdminStatsDTO;
import autosearch.proj.application.DTOs.CarDTO;
import autosearch.proj.application.DTOs.ScrapeSummaryDTO;
import autosearch.proj.application.Services.AdminService;
import autosearch.proj.application.Services.CarService;
import autosearch.proj.application.Services.ScraperServiceImpl;

@RestController
public class AdminController {

	// scraper service object
	@Autowired
	private ScraperServiceImpl scraperServ;

	@Autowired
	private CarService carService;

	@Autowired
	private AdminService adminService;

	// admincontroller constructor
	public AdminController(ScraperServiceImpl scraperServ) {
		this.scraperServ = scraperServ;
	}
	// endpoint for triggering scrape manually, will add scheduled later on

	@GetMapping("/adminscrape")
	public ScrapeSummaryDTO triggerScrape() throws IOException {

		return scraperServ.scrapeAndSaveSite();

	}

	//returns 10 most recent cars
	@GetMapping("/admin/getcars")
	@ResponseBody
	public List<CarDTO> getCars() {

		return carService.findAdminCars();
	}

	// controller for returning stats
	@GetMapping("/admin/stats")
	@ResponseBody
	public AdminStatsDTO getStats() {
		return adminService.returnStats();
	}

}
