package com.alura.foro.infra.errores;

public class IntegrityValidation extends RuntimeException {
	
	public IntegrityValidation(String s) {
		super(s);
	}
}
