package autosearch.proj.application.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import autosearch.proj.application.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer>, 
	JpaSpecificationExecutor<User>{

	 User findByUsernameAndPassword(String username, String password);
	 
	 User findByUsername(String username);
	 
	
	 User findById(int id);
	 
	 List<User> findAll(Specification<User> spec);
	 
}
