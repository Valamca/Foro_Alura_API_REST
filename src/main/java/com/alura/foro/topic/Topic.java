package com.alura.foro.topic;

import java.time.LocalDateTime;

import com.alura.foro.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Table(name = "topics")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")

public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String message;
	
	private LocalDateTime date_creation;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String autor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	private boolean activo;
	

	public Topic() {}

	public Topic(Long id, String title, String message, LocalDateTime dateCreation, Status status, String autor,
			Course course) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.date_creation = dateCreation;
		this.status = status;
		this.autor = autor;
		this.course = course;
	}
	
	public Topic(String title, String message, LocalDateTime dateCreation, Status status, String autor, Course course) {
		this.activo = true;
		this.title = title;
		this.message = message;
		this.date_creation = dateCreation;
		this.status = status;
		this.autor = autor;
		this.course = course;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateCreation() {
		return date_creation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.date_creation = dateCreation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void updateTopicData(UpdateTopicData updateTopicDate) {
		
		if(updateTopicDate.title() != null) {
			this.title = updateTopicDate.title();
		}
		
		if(updateTopicDate.message() != null) {
			this.message = updateTopicDate.message();
		}
		
		if(updateTopicDate.status() != null) {
			this.status = updateTopicDate.status();
		}
		
	}

	public void disableTopic() {
		this.activo =false;
		
	}

	
}
