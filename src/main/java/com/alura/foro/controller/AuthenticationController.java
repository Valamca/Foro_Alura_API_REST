package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.account.Account;
import com.alura.foro.account.AuthenticationAccountData;
import com.alura.foro.infra.security.JWTTokenData;
import com.alura.foro.infra.security.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager; //Para disparar/iniciar el proceso de autenticación en Spring
	
	@Autowired
	private TokenService service; //Para obtener el Token validado
	
	@PostMapping
	@Operation(summary = "Genera un JWT para nuestra autorización en el sistema",
	description = "Con la información dada: Nombre de usuario y Contraseña, genera un JWT para nuestra correcta autenticación en el sistema",
	tags = "Authentication Controller")
	public ResponseEntity<?> AuthenticateAccount(@RequestBody @Valid AuthenticationAccountData data) {
		Authentication AuthToken = new UsernamePasswordAuthenticationToken(data.username(), data.password()); //Se obtiene el usuario y contraseña
		// para poder autenticarlos y usarlos como un "Authentication"
		
		var authenticateUser = authenticationManager.authenticate(AuthToken); //Necesita una variable "Athentication" para iniciar
		var JWTtoken = service.generateToken((Account) authenticateUser.getPrincipal());
		return ResponseEntity.ok(new JWTTokenData(JWTtoken)); //Retornamos el Ok
	}
	
}
