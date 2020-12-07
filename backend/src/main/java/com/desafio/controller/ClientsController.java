package com.desafio.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.controller.page.PageWrapper;
import com.desafio.model.Client;
import com.desafio.repository.Clients;
import com.desafio.repository.filter.ClientFilter;
import com.desafio.service.RegistrationClientService;

@RestController
@RequestMapping(value="/api")
public class ClientsController {


    @Autowired
	private Clients clients;
    
    @Autowired
    private RegistrationClientService clientService;
    
    
    @GetMapping("/common/clients")
    public PageWrapper<Client> list(ClientFilter clientFilter,
    		@PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest) {

    	PageWrapper<Client> clienteWrapper = new PageWrapper<>(clients.filtrar(clientFilter, pageable), httpServletRequest);
    	
    	return clienteWrapper;
    }
    
    @GetMapping(path = {"/admin/clients/{id}"})
    public ResponseEntity view(@PathVariable long id) {
  	
    	return clients.findById(id)
    	           .map(record -> ResponseEntity.ok().body(record))
    	           .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/admin/clients")
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) {
  	
    	clientService.save(client);
	
    	return new ResponseEntity<>(client, HttpStatus.OK);
    }
    
    @PutMapping(path = {"/admin/clients/{id}"})
    public ResponseEntity<Client> update(@PathVariable long id,
            @RequestBody @Valid Client client) {
  	
    	return clientService.update(id, client);

    }
    
    @DeleteMapping(path = {"/admin/clients/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
  	
    	ResponseEntity<?> response = clientService.delete(id);
    	return response;
    }
   
}
