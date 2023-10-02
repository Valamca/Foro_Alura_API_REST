package com.alura.foro.topic.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.course.CourseRepository;
import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.Status;
import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicDetailData;
import com.alura.foro.topic.TopicRegistrationData;
import com.alura.foro.topic.TopicRepository;
import com.alura.foro.topic.validations.TopicValidations;

@Service
public class PostTopicService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	List<TopicValidations> validations;
	
	public ResponseEntity<TopicDetailData> createTopic(TopicRegistrationData topicRegistrationData, UriComponentsBuilder builder) {
		
		if(!courseRepository.findByNombre(topicRegistrationData.course().nombre())) {
			throw new IntegrityValidation("El Curso seleccionado no fue encontrado");
		}
		
		validations.forEach(valid -> valid.validation(topicRegistrationData));
		
		var course = courseRepository.getByNombre(topicRegistrationData.course().nombre());
																	
		var topic = new Topic(topicRegistrationData.title(), topicRegistrationData.message(), LocalDateTime.now(),Status.ABIERTO, 
								topicRegistrationData.autor(), course);
		topicRepository.save(topic);
		
		URI url=builder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		
		return ResponseEntity.created(url).body(new TopicDetailData(topic));
	}
}
