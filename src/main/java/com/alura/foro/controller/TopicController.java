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

import com.alura.foro.topic.ListTopics;
import com.alura.foro.topic.TopicDetail;
import com.alura.foro.topic.TopicDetailData;
import com.alura.foro.topic.TopicRegistrationData;
import com.alura.foro.topic.UpdateTopicData;
import com.alura.foro.topic.service.PostTopicService;
import com.alura.foro.topic.service.PutTopicService;

import io.swagger.v3.oas.annotations.Operation;

import com.alura.foro.topic.service.DeleteTopicService;
import com.alura.foro.topic.service.GetTopicService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

	@Autowired
	private PostTopicService postService;
	
	@Autowired
	private GetTopicService getService;
	
	@Autowired
	private PutTopicService putService;
	
	@Autowired
	private DeleteTopicService delService;
	
	
	@PostMapping
	@Transactional
	@Operation(summary = "Genera un nuevo Tema en nuestra base de datos",
	description = "Con la información proporcionada: Título, mensaje, autor y curso, se genera un nuevo tema en nuestra base de datos",
	tags = "Topic Controller")
	public ResponseEntity<TopicDetailData> createNewTopic(@RequestBody @Valid TopicRegistrationData topicRegistrationData, UriComponentsBuilder uriComponentsBuilder) {
		var topicResponse = postService.createTopic(topicRegistrationData, uriComponentsBuilder);
		return topicResponse;
	}
	
	@GetMapping
	@ResponseBody
	@Operation(summary = "Lista todos los temas en nuestra base de datos",
	description = "Lista todos los temas disponibles en nuestra base de datos",
	tags = "Topic Controller")
	public ResponseEntity<Page<ListTopics>> listTopics(@PageableDefault(size = 5) Pageable pages) {
		Page<ListTopics> listOfTopics = getService.listTopics(pages);
		return ResponseEntity.ok(listOfTopics);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista un Tema que coincida con el ID",
	description = "Lista el tema que coincida con el ID proporcionado por la URL",
	tags = "Topic Controller")
	public ResponseEntity<TopicDetail> listTopic(@PathVariable Long id) {
		var topic = getService.listTopic(id);
		var topicData = new TopicDetail(topic);
		return ResponseEntity.ok(topicData);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Actualiza un tema de nuestra base de datos",
	description = "Actualiza un tema que coincida con la ID proporcionada por la URL con los datos dados",
	tags = "Topic Controller")
	public ResponseEntity<TopicDetailData> updateTopic(@PathVariable Long id,@RequestBody @Valid UpdateTopicData updateTopicDate) {
		var topic = putService.updateTopic(id, updateTopicDate) ;
		return ResponseEntity.ok(topic);
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Desactiva un tema de nuestra base de datos",
	description = "Desactiva el tema que coincida con el ID proporcionado en la URL, con uno en nuestra base de datos",
	tags = "Topic Controller")
	public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
		delService.disableTopic(id);
		return ResponseEntity.noContent().build();
	}
	
}
