package autosearch.proj.application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autosearch.proj.application.Entities.Car;

//Repository, will add own methods later, but JpaRepository is incredibly strong, supplying 
//methods with strong abstractions making it possible to map to any database regardless of content


public interface CarRepository extends JpaRepository<Car, Integer> {

}
