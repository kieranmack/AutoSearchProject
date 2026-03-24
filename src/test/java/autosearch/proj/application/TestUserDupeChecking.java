package autosearch.proj.application;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import autosearch.proj.application.DTOs.UserDTO;
import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.RolesRepository;
import autosearch.proj.application.Repositories.UserRepository;
import autosearch.proj.application.Services.UserServiceImpl;

@SpringBootTest
public class TestUserDupeChecking {

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RolesRepository roleRepo;
	
	@Autowired
	UserServiceImpl userService;
	
	/*
	@Test
	public void DupeTesting() {
	User user = userRepo.findById(1);
	
	UserDTO notExistingUser = new UserDTO("tmack@888jphogan.com", "Hellothere", "tommack");
	
	UserDTO dbUserDTO = userService.convertToDTO(user);
	
	List<User> dbList = userService.returnAllUsers();
	
	List<UserDTO> dbDTOList = userService.convertToDTOList(dbList);
	
	for(int i = 0; i < dbDTOList.size() -1 ; i++) {
		if(dbDTOList.contains(dbUserDTO)) {
			System.out.println("User already exists in the database, please sign in.");
		}else {
			System.out.println("Succesfully created new user and saved. Please sign in now.");
		}
		
	}
	
	for(int i = 0; i < dbDTOList.size() -1; i++) {
		if(dbDTOList.contains(notExistingUser)) {
			System.out.println("User already exists in database, sign in");
		}else {
			System.out.println("Succesfully created new user and saved, sign in");
		}
	}
	
	
*/	
}	
	
	

