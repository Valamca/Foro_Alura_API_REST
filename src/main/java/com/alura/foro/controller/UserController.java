package com.alura.foro.controller;

import java.net.URI;
import java.util.List;

import javax.swing.SwingContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.user.CreationUserData;
import com.alura.foro.user.UpdateUserData;
import com.alura.foro.user.User;
import com.alura.foro.user.UserDataResponse;
import com.alura.foro.user.UserList;
import com.alura.foro.user.UserReposity;
import com.alura.foro.user.validations.UserValidations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {
	
	@Autowired
	private UserReposity userRepository;
	
	@Autowired
	List<UserValidations> validations;
	
	@PostMapping
	@Transactional
	@Operation(summary = "Registrar un nuevo usuario",
	description = "Utilizando la información de Nombre de usuario y Correo Electrónico se genera un nuevo usuario",
	tags = "User Controller")
	public ResponseEntity<UserDataResponse> createUser(@RequestBody @Valid CreationUserData creationUserData, UriComponentsBuilder builder) {
		
		validations.forEach(u -> u.validation(creationUserData));
		
		User user = new User(creationUserData);
		userRepository.save(user);
		
		URI url = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(url).body(new UserDataResponse(user.getId(), user.getUsername(), user.getEmail()));
	}
	
	@GetMapping
	@ResponseBody
	@Operation(summary = "Lista de todos los usuarios disponibles",
	description = "Imprime la lista de los usuarios registrados en nuestra base de datos",
	tags = "User Controller")
	public ResponseEntity<Page<UserList>> listAllUsers(@PageableDefault(size = 5) Pageable page) {
		return ResponseEntity.ok(userRepository.findByActivoTrue(page).map(UserList::new));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista sólo al usuario con la ID proporcionada por URL",
	description = "Imprime al usuario registrado en nuestra base de datos que coincida con el ID proporcionado en la URL",
	tags = "User Controller")
	public ResponseEntity<UserDataResponse> listAUser(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		return ResponseEntity.ok(new UserDataResponse(user.getId(), user.getUsername(), user.getEmail()));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Actualiza a un Usuario que coincida con el ID proporcionado por URL",
	description = "Actualiza al usuario que coincida con la ID solicitada en la URL con los datos ingresados (Nombre de usuario y Correo Electrónico",
	tags = "User Controller")
	public ResponseEntity<UserDataResponse> updateUser(@RequestBody @Valid UpdateUserData updateUserData, @PathVariable Long id) {
		
		validations.forEach(u -> u.validationU(updateUserData));

		User user = userRepository.getReferenceById(id);
		user.updateUser(updateUserData);
		return ResponseEntity.ok(new UserDataResponse(user.getId(), user.getUsername(), user.getEmail()));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Desactiva al usuario que coincida con el ID proporcionado por la URL",
	description = "Desactiva al usuario que coincida con el ID de la URL en nuestra base de datos",
	tags = "User Controller")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		user.disableUser();
		return ResponseEntity.noContent().build();
	}
	
}
