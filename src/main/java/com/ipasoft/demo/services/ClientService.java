package com.ipasoft.demo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipasoft.demo.domain.entity.Client;
import com.ipasoft.demo.domain.repository.IClientRepository;

@Service
public class ClientService implements IClientService {
	@Autowired
	private IClientRepository clientRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Client> getClients() {
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client getClientById(UUID idClient) {
		return clientRepository.findById(idClient).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public void save(Client client) {
		clientRepository.save(client);
	}
	
	@Override
	@Transactional
	public void delete(UUID idClient) {
		clientRepository.deleteById(idClient);
	}

	@Override
	public List<Client> findCustom(String custom) {
		return clientRepository.findByCustom(custom);
	}
}