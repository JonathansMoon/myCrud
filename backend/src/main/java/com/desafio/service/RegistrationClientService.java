package com.desafio.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.model.Client;
import com.desafio.repository.Clients;
import com.desafio.service.exception.CpfClientReadyRegisterException;

@Service
public class RegistrationClientService {
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		Optional<Client> clientExists = clients.findByCpf(client.getCpfNothingFormate());
		if(clientExists.isPresent()) {
			throw new CpfClientReadyRegisterException("CPF já está cadastrado");
		}
		
		clients.save(client);
	}
	
	@Transactional
	public ResponseEntity<Client> update(long id, Client client) {
		
		return clients.findById(id)
	    		.map(record -> {
					record.setName(client.getName());
					record.setEmail(client.getEmail());
					record.setPhone(client.getPhone());
					record.setAddress(client.getAddress());
		            Client clientRegister = clients.save(record);
		            return ResponseEntity.ok().body(clientRegister);
		        }).orElse(ResponseEntity.notFound().build());
	}
	
	@Transactional
	public ResponseEntity<?> delete(long id) {

		return clients.findById(id)
			.map(record -> {
				clients.deleteById(id);
	            return ResponseEntity.ok().body(record);
	        })
			.orElse(ResponseEntity.notFound().build());
	}

}
