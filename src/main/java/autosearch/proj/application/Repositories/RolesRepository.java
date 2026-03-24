package autosearch.proj.application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import autosearch.proj.application.Entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

	Roles findByRoleType(String roleType);
}
