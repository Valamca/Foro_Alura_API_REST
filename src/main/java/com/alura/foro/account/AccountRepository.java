package com.alura.foro.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountRepository extends JpaRepository<Account, Long>{

	UserDetails findByUsername(String username);

}
