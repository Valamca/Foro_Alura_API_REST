package com.alura.foro.controller;

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

import com.alura.foro.course.Course;
import com.alura.foro.course.CourseRegistrationData;
import com.alura.foro.course.CourseRepository;
import com.alura.foro.course.CoursesList;
import com.alura.foro.course.DataResponseCourse;
import com.alura.foro.course.UpdatingDataCourse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	//Resgitrar 1 curso
	@PostMapping
	@Operation(summary = "Genera un nuevo curso en nuestra base de datos",
	description = "Con la información proporcionada, Nombre de curso y su Categoria, crea un nuevo curso en nuestra base de datos ",
	tags = "Course Controller")
	public ResponseEntity<DataResponseCourse> registerCourse(@RequestBody @Valid CourseRegistrationData courseRegistrationData,UriComponentsBuilder uriComponentsBuilder) {
		var course = new Course(courseRegistrationData);
		courseRepository.save(course);
		
		var url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
		
		return ResponseEntity.created(url).body(new DataResponseCourse(course.getId(), course.getNombre(), course.getCategoria()));
	}
	
	//Obtener todos los cursos en listas de 5 en 5
	@GetMapping
	@Transactional
	@ResponseBody
	@Operation(summary = "Lista los cursos disponibles en nuestra base de datos",
	description = "Lista todos los cursos disponibles registrados en nuestra base de datos",
	tags = "Course Controller")
	public ResponseEntity<Page<CoursesList>> courseList(@PageableDefault(size = 5) Pageable paginacion){
		//return courseRepository.findAll(paginacion).map(CoursesList::new);
		return ResponseEntity.ok(courseRepository.findByActivoTrue(paginacion).map(CoursesList::new));
	}
	
	//Obtener un curso individual
	@GetMapping("/{id}")
	@Transactional
	@Operation(summary = "Genera un curso con todos sus elementos",
	description = "Muestra el curso que coincida en nuestra base de datos con el ID proporcionada por la URL",
	tags = "Course Controlller")
	public ResponseEntity<DataResponseCourse> getOneCourse(@PathVariable Long id) {
		Course course = courseRepository.getReferenceById(id);
		return ResponseEntity.ok(new DataResponseCourse(course.getId(), course.getNombre(), course.getCategoria()));
		
	}
	
	//Actualizar Curso
	@PutMapping
	@Transactional
	@Operation(summary = "Actualiza un curso",
	description = "Con la información dada, actualiza un curso que coincida con la ID dada",
	tags = "Course Controller")
	public ResponseEntity<DataResponseCourse> updateCourse(@RequestBody @Valid UpdatingDataCourse updattingDataCourse) {
		Course course = courseRepository.getReferenceById(updattingDataCourse.id());
		course.updateCourse(updattingDataCourse);
		return ResponseEntity.ok(new DataResponseCourse(course.getId(),course.getNombre(), course.getCategoria()));
	}
	
	//Eliminar datos
	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Desactiva un curso de nuestra base de datos",
	description = "Desactiva el curso que coincida con el ID proporcinada por la URL",
	tags = "Course Controller")
	public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
		Course course = courseRepository.getReferenceById(id);
		course.desactivateCourse();
		return ResponseEntity.noContent().build();
	}
	
}
