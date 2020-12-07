package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.model.Client;
import com.desafio.repository.helper.client.ClientsQueries;

@Repository
public interface Clients extends JpaRepository<Client, Long>, ClientsQueries{
	public Optional<Client> findByCpf(String cpf);
}
