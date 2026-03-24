package autosearch.proj.application;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.UserRepository;
import autosearch.proj.application.Services.UserService;

@SpringBootTest
public class TestUserRoleCheck {

	//beginning to test the user role check on sign in, March 23rd
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	private String username = "kieranmack";
	private String password = "thisismypassword";
	@Test
	public void testSignIn() {
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		if(user == null) {
			System.out.println("user not found");
		}else {
			System.out.println(user.getUsername());
		}
		
		//figured it out, one typo and I had to reorganize a ton of stuff 
	
		
		System.out.println(user.getPassword());
		
		System.out.println(user.getRole());
		
		System.out.println(user.getRole().getRoleType());
		
		//now we will do role check.  
		
		
		//do new user that doesn't exist. 
		
		String user1 = "hello";
		
		String pass1 = "world";
		
		User userNew = userRepository.findByUsernameAndPassword(user1, pass1);
		try {
		
		if(userNew.getRole().getRoleType().equals("User")) {
			System.out.println("user screen");
		}else {
			System.out.println("admin screen");
		}
			}catch (NullPointerException e){
			System.out.println(e.getMessage());
		}
		
		
		try {
			
			if(user.getRole().getRoleType().equals("User")) {
				System.out.println("user screen");
			}else {
				System.out.println("admin screen");
			}
				}catch (NullPointerException e){
				System.out.print(e.getMessage());
			}
			
		//Role Check complete
	}
}
