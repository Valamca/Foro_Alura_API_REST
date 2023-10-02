package com.alura.foro.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TopicRepository extends JpaRepository<Topic, Long> {
	@Query("""
			SELECT COUNT (t) > 0
			FROM Topic t
			WHERE t.activo=:activo
			
			""")
	boolean	findByActivo(@Param("activo") boolean activo) ;

	@Query("""
			SELECT COUNT (t) > 0
			FROM Topic t
			WHERE t.message=:message
			
			""")
	boolean existsByMessage(@Param("message") String message);

	boolean existsByTitle(String title);

	Page<Topic> findByActivoTrue(Pageable pages);

}
