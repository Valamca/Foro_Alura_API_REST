package com.alura.foro.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long>{


	boolean existsByAnswer(String answer);

	Page<Answer> findByActiveTrue(Pageable page);


}
