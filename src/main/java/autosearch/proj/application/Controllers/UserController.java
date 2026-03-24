package autosearch.proj.application.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Services.UserService;
import autosearch.proj.application.Services.UserServiceImpl;

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
	
	@PostMapping("/user/register/")
	@ResponseBody
	public String registerUser(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email) {
		return userService.registerUser(username, password, email);
	}
	
	@PostMapping("/user/login/")
	@ResponseBody
	public String loginUser(
			@RequestParam String username,
			@RequestParam String password) {
		return userService.loginUser(username, password);
	}
}
