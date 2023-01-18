package com.ipasoft.demo.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	private static final long serialVersionUID = -7168314576437992120L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, length = 100, unique = false)
	private String name;
	
	private String surname;
	private String email;
	
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@PrePersist
	public void prePersist() {
		this.name += new Date();
	}

	public UUID getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getEmail() {
		return email;
	}
	public Date getBirthDate() {
		return birthDate;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public boolean hasId() {
		return this.getId() != null;
	}
}