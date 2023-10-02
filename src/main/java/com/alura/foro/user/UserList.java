package com.alura.foro.user;

public record UserList(String username, String email) {

	public UserList(User user) {
		this(user.getUsername(), user.getEmail());
	}
	
}
