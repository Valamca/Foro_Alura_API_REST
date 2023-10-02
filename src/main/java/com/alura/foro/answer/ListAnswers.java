package com.alura.foro.answer;

import java.time.LocalDateTime;

import com.alura.foro.topic.TopicDetailData;
import com.alura.foro.user.UserDataResponse;

public record ListAnswers(TopicDetailData topic, Long idAnswer, String answer, LocalDateTime date, UserDataResponse user) {

	public ListAnswers(Answer answer) {
		this(new TopicDetailData(answer.getTopic()),answer.getId(), answer.getAnswer(), answer.getAnswerDate(),
				new UserDataResponse(answer.getUser().getId(), answer.getUser().getUsername(), answer.getUser().getEmail()));
	}
}
