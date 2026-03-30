package autosearch.proj.application.Services;

import java.util.List;

import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

	public List<User> returnAllUsers();
	
	
	public String registerUser(String username, String password, String email);
	
	public String loginUser(String username, String password, HttpSession session);
	
	public List<UserDTO> returnUsers(String roleType);
	
	
	
}
