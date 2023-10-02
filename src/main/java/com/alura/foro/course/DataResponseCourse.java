package com.alura.foro.course;

public record DataResponseCourse(Long id, String nombre, Categories categoria) {
	
	public DataResponseCourse(Long id, String nombre, Categories categoria) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
	}
}
