package autosearch.proj.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//Main class that runs the entire project, nothing will change here. 
@SpringBootApplication
@EnableScheduling
public class AutoSearchProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoSearchProjectApplication.class, args);
	}

}
