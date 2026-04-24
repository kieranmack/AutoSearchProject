package autosearch.proj.application.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import autosearch.proj.application.ApiResponse.ApiResponse;
import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.RolesRepository;
import autosearch.proj.application.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	// will make the default set value to be user, for now, will allow admin
	// to change later directly.

	@Override
	public List<User> returnAllUsers() {

		return userRepository.findAll();
	}

	// registering user to database
	public ApiResponse<Void> registerUser(String username, String password, String email) {

		List<UserDTO> dbDTOList = new ArrayList<>();
		List<User> dbList = userRepository.findAll();
		dbDTOList = convertToDTOList(dbList);

		// create object with default role of "user"
		Roles role = roleRepository.findByRoleType("User");
		User user = new User();
		String hashPassword = hashPassword(password);
		user.setEmail(email);
		user.setPassword(hashPassword);
		user.setUsername(username);
		user.setRole(role);

		// convert that object to a DTO for comparison
		UserDTO userDTO = convertToDTO(user);

		// based upon the boolean value of the compare for dupes, determine whether to
		// save,
		// or to ask the user to try again
		boolean check = compareForDupes(userDTO, dbDTOList);

		if (check) {
			return new ApiResponse<Void>(false, "A user with the same username or email already existsS", null);
		} else

			userRepository.save(user);
		return new ApiResponse<Void>(true, "User succesfully saved", null);

	}

	// login user, straight forward, find by username,
	// if not a match prompt user to try again
	// then use Spring security password encoder to check if raw password == hash
	// password.
	// save session so future requests persist.
	@Override
	public ApiResponse<Void> loginUser(String username, String password, HttpSession session) {

		User user = userRepository.findByUsername(username);

		if (user == null) {
			return new ApiResponse<Void>(false, "username or password incorrect, try again", null);
		}
		Roles role = user.getRole();
		String roleType = role.getRoleType();
		boolean authPassword = passwordEncoder.matches(password, user.getPassword());

		if (!authPassword) {
			return new ApiResponse<Void>(false, "username or password incorrect, try again", null);
		}

		// session created when logged in, user data saved.
		session.setAttribute("loggedIn", user);

		if (roleType.equals("Admin")) {
			return new ApiResponse<Void>(true, "Admin", null);
		}

		return new ApiResponse<Void>(true, "User", null);

	}

	// probably dont need this but it helps for code readability
	private String hashPassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);

	}

	// same method as car but this time for users.
	private UserDTO convertToDTO(User user) {
		String newEmail = user.getEmail();

		String newUser = user.getUsername();

		UserDTO userDTO = new UserDTO(newUser, newEmail);
		return userDTO;
	}

	// same method as car List but for user list.
	private List<UserDTO> convertToDTOList(List<User> dbList) {
		List<UserDTO> returnList = new ArrayList<>();
		for (User user : dbList) {
			UserDTO userToAdd = convertToDTO(user);
			returnList.add(userToAdd);
		}
		return returnList;
	}

	// checking if user dto exists in the database, if it does, prompt user to try
	// again
	private boolean compareForDupes(UserDTO userDTO, List<UserDTO> dbList) {
		for (UserDTO user : dbList) {
			if (user.getUsername().equals(userDTO.getUsername()) || user.getEmail().equals(userDTO.getEmail())) {
				return true;
			}
		}
		return false;
	}

	// code for returning by role, will now test.
	public List<UserDTO> returnUsers(String roleType) {
		List<User> dbList = userRepository.findByRole_roleType(roleType);
		List<UserDTO> returnList = new ArrayList<>();
		returnList = convertToDTOList(dbList);
		return returnList;
	}

	//checking if logged in, if so, return user data values for frontend
	public ApiResponse<UserDTO> isLoggedIn(HttpSession session) {

		User user = (User) session.getAttribute("loggedIn");

		if (user == null) {
			return new ApiResponse<UserDTO>(false, "User must be logged in", null);
		}
		UserDTO dto = convertToDTO(user);

		return new ApiResponse<UserDTO>(true, "User is logged in", dto);

	}
	
	//logout function, very simple
	
	public ApiResponse<Void> logOut(HttpSession session){
		
		session.invalidate();
		return new ApiResponse<Void>(true, "Logged out.", null);
	}

}
