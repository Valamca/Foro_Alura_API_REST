package com.alura.foro.answer;

import java.time.LocalDateTime;

import com.alura.foro.topic.Topic;
import com.alura.foro.topic.TopicDetailData;
import com.alura.foro.user.User;
import com.alura.foro.user.UserDataResponse;

public record DataAnswerResponse(TopicDetailData topic, Long idAnswer, String answer, LocalDateTime date, UserDataResponse user) {

	public DataAnswerResponse(Topic topic, Answer answer, User user) {
		this (new TopicDetailData(topic),answer.getId(), answer.getAnswer(), answer.getAnswerDate(),
				new UserDataResponse(user.getId(), user.getUsername(), user.getEmail()));
	}

}
