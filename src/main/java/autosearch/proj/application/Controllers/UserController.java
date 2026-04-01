package autosearch.proj.application.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import autosearch.proj.application.ApiResponse.ApiResponse;
import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Services.UserService;
import autosearch.proj.application.Services.UserServiceImpl;
import jakarta.servlet.http.HttpSession;

//Rest API Annotation

@RestController
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/admindashboard/")
	@ResponseBody
	public List<UserDTO> returnUsersSpec(
			 String roleType){
			
		return userService.returnUsers(roleType);
	}
	
	
	//register endpoint
	@PostMapping("/user/register/")
	@ResponseBody
	public ApiResponse registerUser(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email) {
		return userService.registerUser(username, password, email);
	}
	
	//user login endpoint
	@PostMapping("/user/login/")
	@ResponseBody
	public ApiResponse loginUser(
			@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		return userService.loginUser(username, password, session);
	}
	
	
	
	
	
	
}
