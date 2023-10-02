package com.alura.foro.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.foro.account.AccountRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Obtener el Token desde el Header ¡Dónde añader el token como autenticación!
		
		var autHeader = request.getHeader("Authorization"); //El header llega en el Authorization
		
		if(autHeader != null) {
			var token = autHeader.replace("Bearer ",""); //Para obtener solo el Token sin el Bearer, solo el String
			var username = tokenService.getSubject(token); //Obtener un Token validado
			if(username != null) {
				var usuario = accountRepository.findByUsername(username); //Obtener mi usuario de mi tabla Account, con el Token validado
				var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities()); //Forzar inicio de sesion
				//Athentication recive el usuario, null (Porque no tiene credenciales), y authorities porque ya está registrado 
				SecurityContextHolder.getContext().setAuthentication(authentication);//Para autenticar usuario, desde un authentication
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
}
