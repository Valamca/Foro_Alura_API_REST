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

import com.alura.foro.answer.AnwersUpdatingData;
import com.alura.foro.answer.CreationAnswerData;
import com.alura.foro.answer.DataAnswerResponse;
import com.alura.foro.answer.ListAnswers;
import com.alura.foro.answer.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private AnswerService service;
	
	@PostMapping
	@Transactional
	@Operation(summary = "Creación de una nueva respuesta en nuestra base de datos",
	description = "Con la información ingresada se puede generar una respuestas que esta relacionada con el tema al que responde, así como e lusuario que "
			+ "la generó.",
	tags = "Answer Controller")
	public ResponseEntity<DataAnswerResponse> createAnswer(@RequestBody @Valid CreationAnswerData creationAnswerData, UriComponentsBuilder builder) {
		var response = service.createAnswer(creationAnswerData, builder);
		return response;
	}
	
	@GetMapping
	@ResponseBody
	@Operation(summary = "Listado de todas las respuestas listadas en nuestra base de datos",
	description = "Genera una lista con todas las respuestas que existan en nuestra base de datos",
	tags = "Answer Controller")
	public ResponseEntity<Page<ListAnswers>> answersList(@PageableDefault(size = 5) Pageable page) {
		var response = service.answersList(page);
		return response;
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista una respuesta en especifico de nuestra base de datos",
	description = "Genera una respuesta con sus elementos, cuando el ID proporcionado en la URL coincida en nuestra base de datos",
	tags = "Answer Controller")
	public ResponseEntity<DataAnswerResponse> answerList(@PathVariable Long id) {
		var response = service.answerList(id);
		return response;
	}
	
	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Actualiza una respuesta de nuestra base de datos",
	description = "Actualiza la respuesta de un elemento de nuestra base de datos que coincida con el ID proporcionado en la URL",
	tags = "Answer Controller")
	public ResponseEntity<DataAnswerResponse> updateAnswer(@RequestBody @Valid AnwersUpdatingData anserUpdatingData, @PathVariable Long id) {
		var response = service.updateAnswer(anserUpdatingData, id);
		return response;
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Desactiva una respuesta de nuestra base de datos",
	description = "Desactiva una respuesta de nuestra base de datos que coincida con el ID proporcionado  en la URL",
	tags = "Answer Controller")
	public ResponseEntity<?> disableAnswer(@PathVariable Long id) {
		var response = service.disableAnswer(id);
		return response;
	}
	
}
