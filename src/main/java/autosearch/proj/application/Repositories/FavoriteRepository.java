package autosearch.proj.application.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import autosearch.proj.application.Entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	List<Favorite> findByUserId(int id);
}
