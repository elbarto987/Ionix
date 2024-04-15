package Ionix.entities;

import Ionix.validation.Create;
import Ionix.validation.IsExistsDb;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;
	
	@Column
	@NotBlank(message = "{NotBlank.user.name}")
	@Size(min = 3, max = 12, message = "{Size.user.name}")
	private String name;
	
	@Column
	@NotBlank(message = "{NotBlank.user.username}")
	@Size(min = 4, max = 12, message = "{Size.user.username}" )
	private String username;
	
	@Column
	@IsExistsDb(groups = Create.class)
	@NotBlank(groups = Create.class, message = "{NotBlank.user.email}")
	@Email(groups = Create.class, message = "{Email.user.email}")
	private String email;
	
	@Column
	@Size(max = 10, min = 10, message = "{Size.user.phone}")
	@NotBlank(message = "{NotBlank.user.phone}")
	@Pattern(regexp = "\\d+", message = "{Pattern.user.phone}")
	private String phone;

	public User() {
	}

	public User(String name, String username, String email, String phone) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
