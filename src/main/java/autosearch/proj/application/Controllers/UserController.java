package autosearch.proj.application.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Services.UserService;

//Rest API Annotation

@RestController
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public List<User> returnAllUsers() {
		
		return userService.returnAllUsers();
	}
}
