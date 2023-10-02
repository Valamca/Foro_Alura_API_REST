package com.alura.foro.topic;

public record UpdateTopicData(

		String title, 
		String message, 
		Status status) {

}
