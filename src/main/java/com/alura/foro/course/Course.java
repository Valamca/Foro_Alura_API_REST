package com.alura.foro.course;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Table(name = "courses")
@Entity(name = "Course")
@EqualsAndHashCode(of = "id")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Categories categoria;
	
	private boolean activo;

	public Course() {}
	
	public Course(String nombre, Categories categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	
	public Course(Long id, String nombre, Categories categoria, Boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.activo = activo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categories getCategoria() {
		return categoria;
	}

	public void setCategoria(Categories categoria) {
		this.categoria = categoria;
	}
	
	public Course(CourseRegistrationData courseRegistrationData) {
		this.activo = true;
		this.nombre = courseRegistrationData.nombre();
		this.categoria = courseRegistrationData.categoria();
	}
	
	public void updateCourse(UpdatingDataCourse updatingDataCourse) {
		if(updatingDataCourse.nombre() != null) {
			this.nombre = updatingDataCourse.nombre();
		}
		
		if(updatingDataCourse.categoria() != null) {
			this.setCategoria(updatingDataCourse.categoria());
		}
	}

	public void desactivateCourse() {
		this.activo = false;
	}

}
