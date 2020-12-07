package com.desafio.repository.helper.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.model.Client;
import com.desafio.repository.filter.ClientFilter;

public interface ClientsQueries {
	public Page<Client> filtrar(ClientFilter filterParams, Pageable pageable);
}
