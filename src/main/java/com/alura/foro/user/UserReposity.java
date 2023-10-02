package com.alura.foro.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposity extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Page<User> findByActivoTrue(Pageable page);

	User getByUsername(String username);

}