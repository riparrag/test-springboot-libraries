package com.ipasoft.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipasoft.demo.controllers.dto.ResponseApiEntityBaseDTO;
import com.ipasoft.demo.domain.entity.Client;
import com.ipasoft.demo.services.IClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping("/get-by-id/{idClient}")
	public ResponseEntity<ResponseApiEntityBaseDTO<Client>> getById(@PathVariable("idClient") UUID idClient) {
		Client client = clientService.getClientById(idClient);
		
		ResponseApiEntityBaseDTO<Client> response = new ResponseApiEntityBaseDTO<Client>( client );

		
		return new ResponseEntity<ResponseApiEntityBaseDTO<Client>>( response, HttpStatus.OK );
	}
	
	@GetMapping("/get-by-email/{email}")
	public List<Client> getById(@PathVariable("email") String email) {
		return clientService.findCustom(email);
	}
	
	@GetMapping("/get-all")
	public List<Client> getAll() {
		return clientService.getClients();
	}
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createClient(@RequestBody Client client) {
		clientService.save( client );
	}
	
	@PutMapping("/update/{idClient}")
	public void updateClient(@RequestBody Client client, @PathVariable("idClient") UUID idClient) {
		Client actualClient = clientService.getClientById(idClient);
		
		actualClient.setName( client.getName() ); 
		
		clientService.save( actualClient );
	}
	
	@DeleteMapping("/delete/{idClient}")
	public void delete(@PathVariable("idClient") UUID idClient) {
		clientService.delete(idClient);
	}
}
