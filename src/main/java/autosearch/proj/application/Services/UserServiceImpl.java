package autosearch.proj.application.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import autosearch.proj.application.Entities.User;
import autosearch.proj.application.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public List<User> returnAllUsers() {
	
		return userRepository.findAll();
	}
	
	
	
	
}
