package com.alura.foro.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Las configuraciones se escanean primero
@EnableWebSecurity //Habilita modulo de WebSecurity, sobreescribe el comportamiento de autenticacion
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Tipo de sesión en este caso (Sin autenticacio por usuario y cnotraseña)
				.and()
				.authorizeRequests()
				.requestMatchers(HttpMethod.POST,"/login").permitAll() //Permitir que no pida JWT en la dirección Login
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()
				//.requestMatchers(HttpMethod.DELETE,"/medicos").hasRole("ADMIN") -> Para dar accesos a ciertos roles
				//.requestMatchers(HttpMethod.DELETE,"/pacientes").hasRole("ADMIN") 
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(securityFilter,UsernamePasswordAuthenticationFilter.class) //Para que mi filtro vaya antes que el filtro Spring
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager(); //En el "AuthenticationController" piden que el AthenticationManager, sea ↓
		//inyectado y lo hacemos desde aqui
	}
	
	@Bean//Bean para que este disponible en el contexto de Spring
	public PasswordEncoder passwordEncoder(){ //Para indicar el tipo de codificación de las contraseñas cuando las reciba de la clase "Account"
		return new BCryptPasswordEncoder();
	}
	
}
