package autosearch.proj.application.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import autosearch.proj.application.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	 User findByUsernameAndPassword(String username, String password);
	 
	 User findByUsername(String username);
	 
	
	 
	 User findById(int id);
	 
	 User findByPassword(String password);
	 
	 
}
