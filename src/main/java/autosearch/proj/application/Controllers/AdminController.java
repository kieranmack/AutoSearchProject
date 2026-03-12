package autosearch.proj.application.Controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.Services.ScraperServiceImpl;

@RestController
public class AdminController {
	
	//scraper service object
	private final ScraperServiceImpl scraperServ;
	
	//admincontroller constructor
	public AdminController(ScraperServiceImpl scraperServ) {
		this.scraperServ = scraperServ;
	}
	//endpoint for triggering scrape manually, will add scheduled later on
	
	
		@GetMapping("/adminscrape")
			public String triggerScrape() {
			try {
			scraperServ.scrapeAndSaveSiteA();
			return "Scrape Success!";
		}catch (IOException e) {
			e.printStackTrace();
			return "Time to debug" +  e.getMessage();
		}
			
		
		}
		
}
