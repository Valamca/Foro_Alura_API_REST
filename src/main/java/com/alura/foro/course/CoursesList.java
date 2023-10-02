package com.alura.foro.course;

public record CoursesList(String nombre, Categories categoria) {

	public CoursesList(Course course) {
		this(course.getNombre(),course.getCategoria());
	}
}
