package autosearch.proj.application.Repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import autosearch.proj.application.Entities.Car;

//Repository, will add own methods later, but JpaRepository is incredibly strong, supplying 
//methods with strong abstractions making it possible to map to any database regardless of content

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>,
	JpaSpecificationExecutor<Car> {

	List<Car> findAll(Specification<Car> spec);
	
	@Query("SELECT DISTINCT c.make from Car c")
	List<String> findDistinctMake();
	
}