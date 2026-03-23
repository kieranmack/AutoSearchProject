package autosearch.proj.application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Object class for Roles, needed for role check logic later on.  When deciding to return
//Entity objects, or DTOs.
@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	private String RoleType;

	public String getRoleType() {
		return RoleType;
	}

	public void setRole(String roleType) {
		this.RoleType = roleType;
	}
	
	
}
