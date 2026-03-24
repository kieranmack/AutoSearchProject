package autosearch.proj.application.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.Car;
import autosearch.proj.application.Entities.Roles;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.RolesRepository;
import autosearch.proj.application.Repositories.UserRepository;

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

	// registering user to database, need to check if they exist in database first.
	// separate method to check if instance of user exists, need to create UserDTO
	// for that.
	public String registerUser(String username, String password, String email) {
		List<UserDTO> dbDTOList = new ArrayList<>();
		List<User> dbList = userRepository.findAll();
		dbDTOList = convertToDTOList(dbList);
		
		//create object with default role of "user"
		Roles role = roleRepository.findByRoleType("User");
		User user = new User();
		String hashPassword = hashPassword(password);
		user.setEmail(email);
		user.setPassword(hashPassword);
		user.setUsername(username);
		user.setRole(role);
		
		//convert that object to a DTO for comparison
		UserDTO userDTO = convertToDTO(user);
		
		//based upon the boolean value of the compare for dupes, determine whether to save,
		//or to ask the user to try again 
		boolean check = compareForDupes(userDTO, dbDTOList);
		
		if(check) {
			return("User cannot be saved, already exists in database");
		}
		
		userRepository.save(user);
		return "User successfully saved in database";

	}
	
	//login user, straight forward, find by username, 
	//if not a match prompt user to try again
	//then use Spring security password encoder to check if raw password == hash password. 
	@Override
	public String loginUser(String username, String password) {
		
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return "Try again, username or password incorrect.";
		}
		boolean authPassword = passwordEncoder.matches(password, user.getPassword());
		
		if(!authPassword) {
			return "Try again, username or password incorrect.";
		}
		
		return "Success! Moving to Search Screen";
	}
	
	

	//probably dont need this but it helps for code readability
	private String hashPassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);

	}

	// same method as car but this time for users.
	private UserDTO convertToDTO(User user) {
		String newEmail = user.getEmail();
		String newPass = user.getPassword();
		String newUser = user.getUsername();
		
		UserDTO userDTO = new UserDTO(newUser, newPass, newEmail);
		return userDTO;
	}
	
	// same method as car List but for user list.
	private List<UserDTO> convertToDTOList(List<User> dbList){
		List<UserDTO> returnList = new ArrayList<>();
		for(User user : dbList) {
			UserDTO userToAdd = convertToDTO(user);
			returnList.add(userToAdd);
		}
		return returnList;
	}
	
	//checking if user dto exists in the database, if it does, prompt user to try again
	private boolean compareForDupes(UserDTO userDTO, List<UserDTO> dbList) {
		for(UserDTO user : dbList) {
			if(userDTO.equals(user)) {
				return true;
			}
		}
		return false;
	}

}
	

	

