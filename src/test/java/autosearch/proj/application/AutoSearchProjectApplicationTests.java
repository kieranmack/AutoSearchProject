package autosearch.proj.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.UserRepository;

@SpringBootTest
class AutoSearchProjectApplicationTests {
	
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}
	
	
}

