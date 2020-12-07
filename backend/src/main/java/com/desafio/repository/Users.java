package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.model.User;

@Repository
public interface Users extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
