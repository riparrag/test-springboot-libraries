package com.ipasoft.demo.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ipasoft.demo.domain.entity.Client;

public interface IClientRepository extends JpaRepository<Client, UUID> {

	List<Client> findByEmail(String email);
	List<Client> findByBirthDate(Date birthDate);
	List<Client> findByName(String name);
	List<Client> findBySurname(String surname);
	
	@Query("select c from Client c where c.email like %?1")
	List<Client> findByCustom(String custom);
}