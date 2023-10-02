package com.alura.foro.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alura.foro.account.Account;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {

	@Value("${api.security.secret}")
	private String apiSecret;
	
	public String generateToken(Account account) {
		try {	
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("com.alura")
		        .withSubject(account.getUsername())//devolver el usuario
		        .withClaim("id", account.getId()) //Devolver el ID
		        .withExpiresAt(generateExpirationDate())
		        .sign(algorithm);	
		} catch (JWTCreationException exception) {
			throw new RuntimeException();
		}
	}
	
	public String getSubject(String token) {	 //Varificar token
		if(token == null) {  //Validación
			throw new RuntimeException();
		}
		
		DecodedJWT verifier = null; //verificador
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret); //Validamos Firma del Token
		    verifier = JWT.require(algorithm) //Tipo de algoritmo con la cadena secreta
		        .withIssuer("com.alura")//Issuer
		        .build()
		        .verify(token); //verificar
		    //verifier.getSubject(); //verificador
		} catch (JWTVerificationException exception){
			System.out.println(exception.toString()); //Impresión en caso de error en verificación
		}
		
		if(verifier == null||verifier.getSubject() == null) {
			throw new RuntimeException("Verificador inválido"); //Sí no existe el verificador
		}
		return verifier.getSubject();
		
	}
	
	public Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-05:00"));
	}
	
	
}
