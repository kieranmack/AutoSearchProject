package autosearch.proj.application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import autosearch.proj.application.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
