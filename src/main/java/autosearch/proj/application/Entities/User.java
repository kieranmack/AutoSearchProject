package autosearch.proj.application.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	//Using validation dependency, adding annotations to my user attributes
	//this will allow us to make login / register completely seamless later on,
	//if validation checks fail JSON returned will be an error message, front end will handle
	//from there
	
	
	@NotBlank(message = "Username is required")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be atleast 8 characters")
	private String password;
	
	
	@NotBlank(message = "Email is required")
	@Email
	@Column(unique = true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles role;
	
	public int getId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	
}
