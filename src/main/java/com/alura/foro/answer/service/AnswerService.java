package com.alura.foro.answer.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.answer.Answer;
import com.alura.foro.answer.AnswerRepository;
import com.alura.foro.answer.AnwersUpdatingData;
import com.alura.foro.answer.CreationAnswerData;
import com.alura.foro.answer.DataAnswerResponse;
import com.alura.foro.answer.ListAnswers;
import com.alura.foro.answer.service.validations.AnswerValidations;
import com.alura.foro.answer.service.validations.UniqueAnswerValidation;
import com.alura.foro.infra.errores.IntegrityValidation;
import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicRepository;
import com.alura.foro.user.User;
import com.alura.foro.user.UserReposity;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private UserReposity userReposity;
	
	@Autowired
	List<AnswerValidations> validations;
	
	public ResponseEntity<DataAnswerResponse> createAnswer(@Valid CreationAnswerData creationAnswerData, UriComponentsBuilder builder) {
		
		validations.forEach(A -> A.validations(creationAnswerData));
		
		Topic topic = topicRepository.getReferenceById(creationAnswerData.topicId());
		User user = userReposity.getByUsername(creationAnswerData.username());
		Answer answer = new Answer(topic, creationAnswerData.answer(), user);
		answerRepository.save(answer);
		
		URI url = builder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();
		
		return ResponseEntity.created(url).body(new DataAnswerResponse(topic, answer, user)) ;
	}

	
	public ResponseEntity<Page<ListAnswers>>  answersList(Pageable page) {
		Page<ListAnswers> answers = answerRepository.findByActiveTrue(page).map(ListAnswers::new);
		return ResponseEntity.ok(answers);
	}


	public ResponseEntity<DataAnswerResponse> answerList(Long id) {
		if(!answerRepository.getReferenceById(id).isActive()) {
			throw new IntegrityValidation("¡Este elemento no existe!");
		}
		
		Answer answer = answerRepository.getReferenceById(id);
		DataAnswerResponse answerData = new DataAnswerResponse(answer.getTopic(),answer, answer.getUser());
		 return ResponseEntity.ok(answerData);
	}


	public ResponseEntity<DataAnswerResponse> updateAnswer(@Valid AnwersUpdatingData anserUpdatingData, Long id) {
		if(!answerRepository.existsById(id)) {
			throw new IntegrityValidation("La respuesta relacionada con este ID no existe.");
		}
		
		if(answerRepository.existsByAnswer(anserUpdatingData.answer())) {
			throw new IntegrityValidation("Respuesta duplicada.");
		}
		Answer answer = answerRepository.getReferenceById(id);
		answer.updateAnswer(anserUpdatingData);		
		return ResponseEntity.ok(new DataAnswerResponse(answer.getTopic(), answer, answer.getUser()));
	}

	public ResponseEntity<?> disableAnswer(Long id) {
		Answer answer = answerRepository.getReferenceById(id);
		answer.disableAnswer();
		return ResponseEntity.noContent().build();
	}
}
