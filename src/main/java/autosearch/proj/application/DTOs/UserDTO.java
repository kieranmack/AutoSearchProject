package autosearch.proj.application.DTOs;

import java.util.Objects;

public class UserDTO {

	private String username;
	
	private String email;
	
	public UserDTO(String username, String password, String email) {
		this.username = username;
		
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username +  ", email=" + email + "]";
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(email, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(username, other.username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
