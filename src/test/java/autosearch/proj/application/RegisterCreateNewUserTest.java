package autosearch.proj.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.RolesRepository;
import autosearch.proj.application.Repositories.UserRepository;
import autosearch.proj.application.Services.UserService;

@SpringBootTest
public class RegisterCreateNewUserTest {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	RolesRepository roleRepo;
	
	
	
	//creating new user.
	//dont think I need to test that much, just need to create endpoint, maybe implement 
	//password hashing? 
	
	//test passsword hashing. 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	
	/*
	@Test
	public void hashPassword() {
	String rawPassword = "helloworld";
	
	String hashPassword = passwordEncoder.encode(rawPassword);
	
	System.out.println("Raw Password: " + rawPassword + " | " 
		+	"Hashed Password: " +  hashPassword);
	}
	*/
	
	//TEsting new user Creation
	@Test 
	public void testCreateUser() {
		
		Roles role = roleRepo.findByRoleType("User");
		
		
		User user = new User();
		user.setEmail("john@gmail.com");
		user.setUsername("johndoe");
		user.setPassword("helloworld!");
		user.setRole(role);
		userRepo.save(user);
		
		User existUser = userRepo.findByUsername(user.getUsername());
		
		if(user.getEmail().equals(existUser.getEmail())) {
			System.out.println("User succesfully saved");
		}else {
			System.out.println("User not saved");
		}
		
		
		
	}
	
}
