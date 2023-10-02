package com.alura.foro.answer;

import java.time.LocalDateTime;

import com.alura.foro.topic.Topic;
import com.alura.foro.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Table(name = "answers")
@Entity(name = "Answer")
@EqualsAndHashCode(of = "id")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	
	private String answer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	private LocalDateTime answer_date;
	
	private boolean active;

	public Answer() {}

	public Answer(Long id, Topic topic, String answer, User user, LocalDateTime answerDate, boolean active) {
		this.id = id;
		this.topic = topic;
		this.answer = answer;
		this.user = user;
		this.answer_date = answerDate;
		this.active = active;
	}

	public Answer(Topic topic, String answer, User user) {
		this.topic = topic;
		this.answer = answer;
		this.user = user;
		this.answer_date = LocalDateTime.now();
		this.active = true;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getAnswerDate() {
		return answer_date;
	}

	public void setAnswerDate(LocalDateTime answerDate) {
		this.answer_date = answerDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void updateAnswer(@Valid AnwersUpdatingData anserUpdatingData) {
		if(anserUpdatingData.answer() != null) {
			this.answer = anserUpdatingData.answer();
		}
	}

	public void disableAnswer() {
		this.active = false;	
	}
		
}
