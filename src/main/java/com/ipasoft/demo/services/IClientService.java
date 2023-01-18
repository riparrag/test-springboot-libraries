package com.ipasoft.demo.services;

import java.util.List;
import java.util.UUID;

import com.ipasoft.demo.domain.entity.Client;

public interface IClientService {
	public List<Client> getClients();
	public void save(Client client);
	public Client getClientById(UUID idClient);
	public void delete(UUID idClient);
	List<Client> getClientByEmail(String email);
	public List<Client> findCustom(String email);
}