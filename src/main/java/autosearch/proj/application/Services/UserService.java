package autosearch.proj.application.Services;

import java.util.List;



import autosearch.proj.application.ApiResponse.ApiResponse;
import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

	public List<User> returnAllUsers();
	
	
	public ApiResponse<Void> registerUser(String username, String password, String email);
	
	public ApiResponse<Void> loginUser(String username, String password, HttpSession session);
	
	public List<UserDTO> returnUsers(String roleType);

	public ApiResponse<UserDTO> isLoggedIn(HttpSession session);
	
	public ApiResponse<Void> logOut(HttpSession session);

	
	
	
	
}
