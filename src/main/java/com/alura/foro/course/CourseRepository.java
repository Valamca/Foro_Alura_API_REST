package com.alura.foro.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long>{

	Page<Course> findByActivoTrue(Pageable paginacion);
	
	@Query("""
			SELECT COUNT(c) > 0
			FROM Course c
			WHERE c.activo=true
			AND c.nombre=:name
			""")
	boolean findByNombre(@Param("name") String name);

	
	
	@Query("""
			SELECT c
			FROM Course c
			WHERE c.activo=true
			AND c.nombre=:name
			""")
	Course getByNombre(@Param("name") String nombre);
	
}
