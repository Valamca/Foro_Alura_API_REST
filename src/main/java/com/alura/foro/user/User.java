package com.alura.foro.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Table(name = "users")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String email;
	
	private boolean activo;

	public User() {}

	public User(Long id, String username, String email, String password, boolean active) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.activo = active;
	}

	public User(@Valid CreationUserData creationUserData) {
		this.username = creationUserData.username();
		this.email = creationUserData.email();
		this.activo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public boolean isActive() {
		return activo;
	}

	public void setActive(boolean active) {
		this.activo = active;
	}

	public void updateUser(@Valid UpdateUserData updateUserData) {	
		if(updateUserData.username() != null) {
			this.username = updateUserData.username();
		}
		
		if(updateUserData.email() != null) {
			this.email = updateUserData.email();
		}
		
	}

	public void disableUser() {
		this.activo = false;
	}
	
	

	
}
